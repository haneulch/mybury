package com.mybury.bucketlist.auth.controller;

import com.mybury.bucketlist.auth.annotation.AccessTokenCheck;
import com.mybury.bucketlist.auth.vo.BeforeWriteResponseVO;
import com.mybury.bucketlist.auth.vo.BucketlistRemoveRequestVO;
import com.mybury.bucketlist.auth.vo.BucketlistViewResponseVO;
import com.mybury.bucketlist.auth.vo.CancelBucketlistRequestVO;
import com.mybury.bucketlist.auth.vo.CategoryRequestVO;
import com.mybury.bucketlist.auth.vo.CategoryResponseVO;
import com.mybury.bucketlist.auth.vo.CompleteBucketlistRequestVO;
import com.mybury.bucketlist.auth.vo.CreateCategoryRequestVO;
import com.mybury.bucketlist.auth.vo.DDayResponseVO;
import com.mybury.bucketlist.auth.vo.HomeResponseVO;
import com.mybury.bucketlist.auth.vo.HostSignInResponseVO;
import com.mybury.bucketlist.auth.vo.HostSignUpCheckRequestVO;
import com.mybury.bucketlist.auth.vo.HostSignUpCheckResponseVO;
import com.mybury.bucketlist.auth.vo.MyPageRequestVO;
import com.mybury.bucketlist.auth.vo.NoticeRequestVO;
import com.mybury.bucketlist.auth.vo.NoticeResponseVO;
import com.mybury.bucketlist.auth.vo.PinBucketlistRequestVO;
import com.mybury.bucketlist.auth.vo.RedoBucketlistRequestVO;
import com.mybury.bucketlist.auth.vo.SupportItemsResponseVO;
import com.mybury.bucketlist.auth.vo.WithdrawalRequestVO;
import com.mybury.bucketlist.core.base.BaseResponseVO;
import com.mybury.bucketlist.core.constants.ApiReturnCodes;
import com.mybury.bucketlist.core.constants.CommonCodes;
import com.mybury.bucketlist.core.domain.Bucketlist;
import com.mybury.bucketlist.core.domain.Category;
import com.mybury.bucketlist.core.domain.Notice;
import com.mybury.bucketlist.core.domain.SupportHistory;
import com.mybury.bucketlist.core.domain.SupportItem;
import com.mybury.bucketlist.core.domain.User;
import com.mybury.bucketlist.core.repository.NoticeRepository;
import com.mybury.bucketlist.core.service.BucketlistManager;
import com.mybury.bucketlist.core.service.CategoryManager;
import com.mybury.bucketlist.core.service.HostService;
import com.mybury.bucketlist.core.service.SupportManager;
import com.mybury.bucketlist.core.service.UserManager;
import com.mybury.bucketlist.core.util.JwtUtils;
import com.mybury.bucketlist.core.vo.BucketlistModifyRequestVO;
import com.mybury.bucketlist.core.vo.BucketlistWriteRequestVO;
import com.mybury.bucketlist.core.vo.ChangeOrderListDTO;
import com.mybury.bucketlist.core.vo.CreateProfileRequestVO;
import com.mybury.bucketlist.core.vo.DDayRequestVO;
import com.mybury.bucketlist.core.vo.HomeRequestVO;
import com.mybury.bucketlist.core.vo.HostSignInRequestVO;
import com.mybury.bucketlist.core.vo.HostSignUpRequestVO;
import com.mybury.bucketlist.core.vo.HostSignUpResponseVO;
import com.mybury.bucketlist.core.vo.ModifyCategoryNameRequestVO;
import com.mybury.bucketlist.core.vo.ModifyCategoryPriorityRequestVO;
import com.mybury.bucketlist.core.vo.MyPageResponseVO;
import com.mybury.bucketlist.core.vo.RemoveCategoryRequestVO;
import com.mybury.bucketlist.core.vo.SupportHistoryRequestVO;
import com.mybury.bucketlist.core.vo.SupportItemRequestVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "마이버리", description = "마이버리 API")
public class HostController {

	@Value("${mybury.address}")
	private String authServerAddress;

	@Autowired
	private JwtUtils jwtUtils;

	private final UserManager userManager;
	private final BucketlistManager bucketlistManager;
	private final CategoryManager categoryManager;
	private final NoticeRepository noticeRepository;
	private final SupportManager supportManager;
	private final HostService hostService;

	@Operation(summary = "회원가입 여부 체크")
	@PostMapping(value = "/signup_check")
	public HostSignUpCheckResponseVO signUpCheck(@RequestBody HostSignUpCheckRequestVO requestVO) {
		User user = userManager.getUserByEmail(requestVO.getEmail());
		boolean signUp = (user != null);

		log.info("???" + authServerAddress);
		return new HostSignUpCheckResponseVO(signUp, user);
	}

	@Operation(summary = "회원가입")
	@PostMapping(value = "/signup")
	public HostSignUpResponseVO signUp(@RequestBody HostSignUpRequestVO requestVO) {
		User user = userManager.signup(requestVO);
		return new HostSignUpResponseVO(user);
	}

	@Operation(summary = "로그인")
	@PostMapping(value = "/signin")
	public HostSignInResponseVO signIn(@RequestBody HostSignInRequestVO requestVO) {
		User user = userManager.signin(requestVO);
		String accessToken = jwtUtils.createAccessToken(user.getId());
		String refreshToken = jwtUtils.createRefreshToken(accessToken);
		return new HostSignInResponseVO(accessToken, refreshToken);
	}

	@Operation(summary = "프로필 생성")
	@AccessTokenCheck
	@PostMapping(value = "/profile")
	public BaseResponseVO createProfile(CreateProfileRequestVO requestVO) {
		userManager.createProfile(requestVO);
		return BaseResponseVO.ok();
	}

	@Operation(summary = "홈", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "버킷리스트 목록 request", required = true,
		content = @Content(schema=@Schema(implementation = HomeRequestVO.class))))
	@AccessTokenCheck
	@GetMapping(value = "/home")
	public HomeResponseVO home(HomeRequestVO requestVO) {
		List<Bucketlist> bucketlists = bucketlistManager.getBucketlists(requestVO);

		boolean popupYn = false;
		String popupPeriodStr = "1,7";
		for (String popupPeriod : popupPeriodStr.split(",")) {
			popupYn = bucketlistManager.existsPopupBucketlist(requestVO.getUserId(), Integer.parseInt(popupPeriod));
			if (popupYn) {
				break;
			}
		}
		return new HomeResponseVO(bucketlists, popupYn);
	}

	@Operation(summary = "dday별 버킷리스트 조회")
	@AccessTokenCheck
	@GetMapping(value = "/dDay")
	public DDayResponseVO dDay(DDayRequestVO requestVO) {
		List<DDayResponseVO.DDayVO> dDayVOList = new ArrayList<>();
		List<Bucketlist> dDayBucketlists = bucketlistManager.getDDayBucketlist(requestVO.getUserId(), requestVO.getFilter());

		int day = -1;
		for (Bucketlist bucketlist : dDayBucketlists) {
			if (day != bucketlist.getDDay()) {
				List<Bucketlist> bucketlists = bucketlistManager.getBucketlistsByDDate(bucketlist.getDDate(),
						requestVO.getUserId());
				DDayResponseVO.DDayVO dDayVO = new DDayResponseVO.DDayVO(bucketlist.getDDay(), bucketlists);
				dDayVOList.add(dDayVO);
			}

			day = bucketlist.getDDay();
		}

		return new DDayResponseVO(dDayVOList);
	}

	@Operation(summary = "버킷리스트 완료")
	@AccessTokenCheck
	@PostMapping(value = "/complete")
	public BaseResponseVO completeBucketlist(@RequestBody CompleteBucketlistRequestVO requestVO) {
		Bucketlist bucketlist = bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
		bucketlist.setUserCount(bucketlist.getUserCount() + 1);

		if (bucketlist.getUserCount() >= bucketlist.getGoalCount()) {
			bucketlist.setStatus(CommonCodes.BucketlistStatus.COMPLETED);
			bucketlist.setCompletedDt(new Date());
		}

		bucketlistManager.saveBucketlist(bucketlist);
		return BaseResponseVO.ok();
	}

	@Operation(description = "버킷리스트 완료 취소")
	@AccessTokenCheck
	@PostMapping(value = "/cancel")
	public BaseResponseVO cancelBucketlist(@RequestBody CancelBucketlistRequestVO requestVO) {
		Bucketlist bucketlist = bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
		bucketlist.setUserCount(bucketlist.getUserCount() - 1);

		if (bucketlist.getUserCount() < bucketlist.getGoalCount()) {
			bucketlist.setStatus(CommonCodes.BucketlistStatus.STARTED);
		}
		
		bucketlistManager.saveBucketlist(bucketlist);
		return BaseResponseVO.ok();
	}

	@Operation(description = "다시 도전하기")
	@AccessTokenCheck
	@PostMapping(value = "/redo")
	public BaseResponseVO redoBucketlist(@RequestBody RedoBucketlistRequestVO requestVO) {
		Bucketlist bucketlist = this.bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
		bucketlist.setUserCount(0);
		bucketlist.setStatus("1");
		this.bucketlistManager.saveBucketlist(bucketlist);
		return BaseResponseVO.ok();
	}

	@Operation(description = "??")
	@AccessTokenCheck
	@PostMapping(value = "/pin")
	public BaseResponseVO pin(@RequestBody PinBucketlistRequestVO requestVO) {
		Bucketlist bucketlist = bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
		if (bucketlist.isPin()) {
			bucketlist.setPin(false);
		} else {
			bucketlist.setPin(true);
		}

		bucketlistManager.saveBucketlist(bucketlist);
		return BaseResponseVO.ok();
	}

	@Operation(description = "사용자별 카테고리 정보")
	@AccessTokenCheck
	@GetMapping(value = "/beforeWrite")
	public BeforeWriteResponseVO beforeWrite(String userId) {
		User user = userManager.getUserById(userId);
		List<Category> categoryList = categoryManager.getCategoryListByUserId(user.getId());
		return new BeforeWriteResponseVO(categoryList);
	}

	/**
	 * 버킷리스트 등록
	 * 
	 * @param requestVO
	 * @return
	 */
	@Operation(description = "버킷리스트 등록")
	@AccessTokenCheck
	@PostMapping(value = "/write")
	public BaseResponseVO writeBucketlist(BucketlistWriteRequestVO requestVO) {
		bucketlistManager.writeBucketlist(requestVO);
		return BaseResponseVO.ok();
	}

	/**
	 * 버킷리스트 상세
	 * 
	 * @param id
	 * @param userId
	 * @return
	 */
	@Operation(description = "버킷리스트 상세")
	@AccessTokenCheck
	@GetMapping(value = "/bucketlist/{id}")
	public BucketlistViewResponseVO getBucketlist(@PathVariable String id, String userId) {
		Bucketlist bucketlist = bucketlistManager.getBucketlistById(id);
		return new BucketlistViewResponseVO(bucketlist);
	}

	/**
	 * 버킷리스트 수정
	 * 
	 * @param id
	 * @param requestVO
	 * @return
	 */
	@AccessTokenCheck
	@PostMapping(value = "/bucketlist/{id}")
	public BaseResponseVO modifyBucketlist(@PathVariable String id, BucketlistModifyRequestVO requestVO) {
		bucketlistManager.modifyBucketlist(requestVO);
		return BaseResponseVO.ok();
	}

	@Operation(description = "버킷리스트 삭제")
	@AccessTokenCheck
	@DeleteMapping(value = "/bucketlist/{id}")
	public BaseResponseVO removeBucketlist(@PathVariable String id, @RequestBody BucketlistRemoveRequestVO requestVO) {
		bucketlistManager.deleteBucketlist(id);
		return BaseResponseVO.ok();
	}

	@AccessTokenCheck
	@GetMapping(value = "/mypage")
	public MyPageResponseVO mypage(MyPageRequestVO requstVO) {
		User user = userManager.getUserById(requstVO.getUserId());

		if (user.getImgUrl() != null) {
			String imgUrl = authServerAddress + user.getImgUrl();
			user.setImgUrl(imgUrl);
		}

		MyPageResponseVO responseVO = new MyPageResponseVO();
		responseVO.setName(user.getName());
		responseVO.setImageUrl(user.getImgUrl());

		int startedCount = bucketlistManager.getStartedBucklistCount(user.getId());
		responseVO.setStartedCount(startedCount);

		int completedCount = bucketlistManager.getCompletedBucketlistCount(user.getId());
		responseVO.setCompletedCount(completedCount);

		int dDayCount = bucketlistManager.getDDayBucketlist(user.getId(), null).size();
		responseVO.setDDayCount(dDayCount);

		List<MyPageResponseVO.CategoryVO> categoryList = setCategoryList(user);
		responseVO.setCategoryList(categoryList);

		responseVO.setReturnCode(ApiReturnCodes.OK);
		return responseVO;
	}

	private List<MyPageResponseVO.CategoryVO> setCategoryList(User user) {
		List<MyPageResponseVO.CategoryVO> categoryList = new ArrayList<>();
		List<Category> userCategoryList = categoryManager.getCategoryListByUserId(user.getId());
		for (Category category : userCategoryList) {
			int categoryCount = 0;
			for (Bucketlist bucketlist : user.getBucketlists()) {
				if (category.equals(bucketlist.getCategory())) {
					categoryCount++;
				}
			}
			MyPageResponseVO.CategoryVO categoryVO = new MyPageResponseVO.CategoryVO(category.getId(), category.getName(), categoryCount);
			categoryList.add(categoryVO);
		}
		return categoryList;
	}

	@AccessTokenCheck
	@GetMapping(value = "/category")
	public CategoryResponseVO getCategory(CategoryRequestVO requestVO) {
		List<Bucketlist> bucketlists = bucketlistManager.getBucketlistByCategoryId(requestVO.getCategoryId());
		return new CategoryResponseVO(bucketlists);
	}

	@AccessTokenCheck
	@PostMapping(value = "/category")
	public BaseResponseVO createCategory(@RequestBody CreateCategoryRequestVO requestVO) {
		User user = userManager.getUserById(requestVO.getUserId());

		Category category = new Category();
		category.setName(requestVO.getName());
		category.setPriority(categoryManager.getLastPriorityCategory(requestVO.getUserId()) + 1);
		category.setUser(user);
		categoryManager.save(category);

		return BaseResponseVO.ok();
	}

	@AccessTokenCheck
	@DeleteMapping(value = "/category")
	public BaseResponseVO removeCategory(@RequestBody RemoveCategoryRequestVO requestVO) {
		categoryManager.remove(requestVO);
		return BaseResponseVO.ok();
	}

	@AccessTokenCheck
	@PostMapping(value = "/category/edit_name")
	public BaseResponseVO modifyCategoryName(@RequestBody ModifyCategoryNameRequestVO requestVO) {
		categoryManager.modifyName(requestVO);
		return BaseResponseVO.ok();
	}

	@AccessTokenCheck
	@PostMapping(value = "/category/edit_priority")
	public BaseResponseVO modifyCategoryPriority(@RequestBody ModifyCategoryPriorityRequestVO requestVO) {
		categoryManager.modifyPriority(requestVO);
		return BaseResponseVO.ok();
	}

	@AccessTokenCheck
	@DeleteMapping(value = "/withdrawal")
	public BaseResponseVO withdrawal(@RequestBody WithdrawalRequestVO requestVO) {
		userManager.remove(requestVO.getUserId());
		return BaseResponseVO.ok();
	}

	/**
	 * 후원
	 * 
	 * @param requestVO
	 * @return
	 */
	@Operation(description = "버킷리스트 수정")
	@AccessTokenCheck
	@PostMapping(value = "/support")
	public BaseResponseVO support(@RequestBody SupportHistoryRequestVO requestVO) {
		SupportHistory history = requestVO.toEntity();
		supportManager.saveSupportHistory(history);
		return BaseResponseVO.ok();
	}

	/**
	 * 후원 아이템 리스트
	 * 
	 * @param requestVO
	 * @return
	 */
	@Operation(description = "후원 아이템 리스트")
	@AccessTokenCheck
	@PostMapping(value = "/support_items")
	public SupportItemsResponseVO supportItems(@RequestBody SupportItemRequestVO requestVO) {
		SupportItemsResponseVO vo = new SupportItemsResponseVO(supportManager.findAllByOrderByItemPrice());

		// 성공여부가 N인 후원이력을 같이 전달한다.
		List<SupportHistory> supportHistory = supportManager.findByUserIdAndSusYnOrderByCreatedDtDesc(requestVO.getUserId(), "N".charAt(0));
		if (supportHistory != null) {
			vo.setRecentSupport(supportHistory);
		}

		String totalPrice = supportManager.getSupportTotalPrice(requestVO.getUserId());
		if (totalPrice == null) {
			totalPrice = "0";
		}
		vo.setTotalPrice(totalPrice);
		return vo;
	}

	/**
	 * 후원 아이템 토큰 및 성공여부 업데이트
	 * 
	 * @param requestVO
	 * @return
	 */
	@Operation(description = "후원 아이템 토큰 및 성공여부 업데이트")
	@AccessTokenCheck
	@PostMapping(value = "/support_edit")
	public BaseResponseVO supportEdit(@RequestBody SupportHistoryRequestVO requestVO) {

		SupportHistory supportHistory = supportManager.findOneByUserIdAndToken(requestVO.getUserId(), requestVO.getToken());

		if (supportHistory != null && !requestVO.getSusYn().equals(supportHistory.getSusYn())) {
			supportHistory.setSusYn(requestVO.getSusYn().charAt(0));
			supportManager.updateSupportHistory(supportHistory);
		}

		return BaseResponseVO.ok();
	}
	
	/**
	 * 버킷리스트 순서 변경
	 * 
	 */
	@Operation(summary = "버킷리스트 순서 변경",
		requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "버킷리스트 순서 변겨 request", required = true,
			content = @Content(schema=@Schema(implementation = ChangeOrderListDTO.class))))
	@AccessTokenCheck
	@GetMapping("/change_order")
	public BaseResponseVO changeOrder(@RequestBody ChangeOrderListDTO requestVO) {
		hostService.changeOrder(requestVO);

		return BaseResponseVO.ok();
	}

	/**
	 * 후원 아이템 수정
	 * 
	 * @param param
	 * @return
	 */
	@PostMapping(value = "/support_items_edit")
	public BaseResponseVO updateSupportItems(@RequestBody List<Map<String, String>> param) {
		List<SupportItem> vo = new ArrayList<SupportItem>();
		for (Map<String, String> m : param) {
			SupportItem item = new SupportItem();
			item.setId(Integer.parseInt(m.get("id")));
			item.setItemPrice(Integer.parseInt(m.get("item_price")));
			item.setItemImg(m.get("item_img"));
			item.setItemName(m.get("item_name"));
			item.setGoogleKey(m.get("google_key"));

			vo.add(item);
		}

		supportManager.saveSupportItem(vo);

		return BaseResponseVO.ok();
	}

	/**
	 * 팝업 정보
	 * @return
	 */
	@Operation(description = "팝업 정보")
	@AccessTokenCheck
	@GetMapping(value = "/notice")
	public BaseResponseVO getNotice() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());

		List<Notice> notice = noticeRepository.findByStartDtLessThanEqualAndEndDtGreaterThanEqualAndDpYn(today, today, "Y".charAt(0));

		NoticeResponseVO vo = new NoticeResponseVO(notice);

		return vo;
	}

	/**
	 * 공지사항 추가
	 * 
	 * @param requestVO
	 * @return
	 */
	@PostMapping(value = "/save_notice")
	public BaseResponseVO saveNotice(@RequestBody NoticeRequestVO requestVO) {

		Notice notice = Notice
							.builder()
							.seq(requestVO.getSeq())
							.title(requestVO.getTitle())
							.content(requestVO.getContent())
							.startDt(requestVO.getStartDt())
							.endDt(requestVO.getEndDt())
							.dpYn(requestVO.getDpYn().charAt(0))
							.build();

		noticeRepository.save(notice);

		return BaseResponseVO.ok();
	}
}
