package com.mybury.bucketlist.auth.controller;

import com.mybury.bucketlist.auth.annotation.AccessTokenCheck;
import com.mybury.bucketlist.auth.constants.ApiUriConstants;
import com.mybury.bucketlist.auth.util.GetPropertyUtils;
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
import com.mybury.bucketlist.core.service.SupportManager;
import com.mybury.bucketlist.core.service.UserManager;
import com.mybury.bucketlist.core.util.JwtUtils;
import com.mybury.bucketlist.core.vo.BucketlistModifyRequestVO;
import com.mybury.bucketlist.core.vo.BucketlistWriteRequestVO;
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

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class HostController {

	private static String authServerAddress = GetPropertyUtils.getProperty("address");

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserManager userManager;

	@Autowired
	private BucketlistManager bucketlistManager;

	@Autowired
	private CategoryManager categoryManager;

	@Autowired
	private NoticeRepository noticeRepository;

	@Autowired
	private SupportManager supportManager;

	@PostMapping(value = ApiUriConstants.SIGNUP_CHECK)
	public HostSignUpCheckResponseVO signUpCheck(@RequestBody HostSignUpCheckRequestVO requestVO) {
		User user = userManager.getUserByEmail(requestVO.getEmail());
		boolean signUp = (user != null);
		return new HostSignUpCheckResponseVO(signUp, user);
	}

	@PostMapping(value = ApiUriConstants.SIGNUP)
	public HostSignUpResponseVO signUp(@RequestBody HostSignUpRequestVO requestVO) {
		User user = userManager.signup(requestVO);
		return new HostSignUpResponseVO(user);
	}

	@PostMapping(value = ApiUriConstants.SIGNIN)
	public HostSignInResponseVO signIn(@RequestBody HostSignInRequestVO requestVO) {
		User user = userManager.signin(requestVO);
		String accessToken = jwtUtils.createAccessToken(user.getId());
		String refreshToken = jwtUtils.createRefreshToken(accessToken);
		return new HostSignInResponseVO(accessToken, refreshToken);
	}

	@AccessTokenCheck
	@PostMapping(value = ApiUriConstants.PROFILE)
	public BaseResponseVO createProfile(CreateProfileRequestVO requestVO) {
		userManager.createProfile(requestVO);
		return BaseResponseVO.ok();
	}

	@AccessTokenCheck
	@GetMapping(value = ApiUriConstants.HOME)
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

	// @AccessTokenCheck
	@GetMapping(value = ApiUriConstants.D_DAY)
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

	@AccessTokenCheck
	@PostMapping(value = ApiUriConstants.BUCKETLIST_COMPLETE)
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

	@AccessTokenCheck
	@PostMapping(value = ApiUriConstants.BUCKETLIST_CANCEL)
	public BaseResponseVO cancelBucketlist(@RequestBody CancelBucketlistRequestVO requestVO) {
		Bucketlist bucketlist = bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
		bucketlist.setUserCount(bucketlist.getUserCount() - 1);

		if (bucketlist.getUserCount() < bucketlist.getGoalCount()) {
			bucketlist.setStatus(CommonCodes.BucketlistStatus.STARTED);
		}
		
		bucketlistManager.saveBucketlist(bucketlist);
		return BaseResponseVO.ok();
	}

	@AccessTokenCheck
	@PostMapping(value = ApiUriConstants.BUCKETLIST_REDO)
	public BaseResponseVO redoBucketlist(@RequestBody RedoBucketlistRequestVO requestVO) {
		Bucketlist bucketlist = this.bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
		bucketlist.setUserCount(0);
		bucketlist.setStatus("1");
		this.bucketlistManager.saveBucketlist(bucketlist);
		return BaseResponseVO.ok();
	}

	@AccessTokenCheck
	@PostMapping(value = ApiUriConstants.BUCKETLIST_PIN)
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

	@AccessTokenCheck
	@GetMapping(value = ApiUriConstants.BUCKETLIST_BEFORE_WRITE)
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
	@AccessTokenCheck
	@PostMapping(value = ApiUriConstants.BUCKETLIST_WRITE)
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
	@AccessTokenCheck
	@GetMapping(value = ApiUriConstants.BUCKETLIST_CRUD)
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
	@PostMapping(value = ApiUriConstants.BUCKETLIST_CRUD)
	public BaseResponseVO modifyBucketlist(@PathVariable String id, BucketlistModifyRequestVO requestVO) {
		bucketlistManager.modifyBucketlist(requestVO);
		return BaseResponseVO.ok();
	}

	@AccessTokenCheck
	@DeleteMapping(value = ApiUriConstants.BUCKETLIST_CRUD)
	public BaseResponseVO removeBucketlist(@PathVariable String id, @RequestBody BucketlistRemoveRequestVO requestVO) {
		bucketlistManager.deleteBucketlist(id);
		return BaseResponseVO.ok();
	}

	@AccessTokenCheck
	@GetMapping(value = ApiUriConstants.MYPAGE)
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
	@GetMapping(value = ApiUriConstants.CATEGORY)
	public CategoryResponseVO getCategory(CategoryRequestVO requestVO) {
		List<Bucketlist> bucketlists = bucketlistManager.getBucketlistByCategoryId(requestVO.getCategoryId());
		return new CategoryResponseVO(bucketlists);
	}

	@AccessTokenCheck
	@PostMapping(value = ApiUriConstants.CATEGORY)
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
	@PostMapping(value = ApiUriConstants.CATEGORY_EDIT_NAME)
	public BaseResponseVO modifyCategoryName(@RequestBody ModifyCategoryNameRequestVO requestVO) {
		categoryManager.modifyName(requestVO);
		return BaseResponseVO.ok();
	}

	@AccessTokenCheck
	@PostMapping(value = ApiUriConstants.CATEGORY_EDIT_PRIORITY)
	public BaseResponseVO modifyCategoryPriority(@RequestBody ModifyCategoryPriorityRequestVO requestVO) {
		categoryManager.modifyPriority(requestVO);
		return BaseResponseVO.ok();
	}

	@AccessTokenCheck
	@DeleteMapping(value = ApiUriConstants.CATEGORY)
	public BaseResponseVO removeCategory(@RequestBody RemoveCategoryRequestVO requestVO) {
		categoryManager.remove(requestVO);
		return BaseResponseVO.ok();
	}

//	@AccessTokenCheck
	@DeleteMapping(value = ApiUriConstants.WITHDRAWAL)
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
	@AccessTokenCheck
	@PostMapping(value = ApiUriConstants.SUPPORT)
	public BaseResponseVO support(@RequestBody SupportHistoryRequestVO requestVO) {
		SupportHistory history = requestVO.toEntity(); 
//				new SupportHistory();
//		history.setItemId(requestVO.getItemId());
//		history.setUserId(requestVO.getUserId());
//		history.setToken(requestVO.getToken());
//		history.setSusYn(requestVO.getSusYn().charAt(0));
		supportManager.saveSupportHistory(history);
		return BaseResponseVO.ok();
	}

	/**
	 * 후원 아이템 리스트
	 * 
	 * @param requestVO
	 * @return
	 */
	@AccessTokenCheck
	@PostMapping(value = ApiUriConstants.SUPPORT_ITEMS)
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
	@AccessTokenCheck
	@PostMapping(value = ApiUriConstants.SUPPORT_EDIT)
	public BaseResponseVO supportEdit(@RequestBody SupportHistoryRequestVO requestVO) {

		SupportHistory supportHistory = supportManager.findOneByUserIdAndToken(requestVO.getUserId(), requestVO.getToken());

		if (supportHistory != null && !requestVO.getSusYn().equals(supportHistory.getSusYn())) {
			supportHistory.setSusYn(requestVO.getSusYn().charAt(0));
			supportManager.updateSupportHistory(supportHistory);
		}

		return BaseResponseVO.ok();
	}

	/**
	 * 후원 아이템 수정
	 * 
	 * @param requestVO
	 * @return
	 */
	@PostMapping(value = ApiUriConstants.SUPPORT_ITEMS_EDIT)
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
	 * 
	 * @param requestVO
	 * @return
	 */
	@AccessTokenCheck
	@GetMapping(value = ApiUriConstants.NOTICE)
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
	@PostMapping(value = ApiUriConstants.NOTICE_SAVE)
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
