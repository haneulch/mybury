package com.mybury.bucketlist.auth.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.mybury.bucketlist.auth.annotation.AccessTokenCheck;
import com.mybury.bucketlist.auth.dto.BucketlistResDTO;
import com.mybury.bucketlist.auth.dto.SearchResDTO;
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
import com.mybury.bucketlist.auth.vo.SearchRequestDTO;
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
import com.mybury.bucketlist.core.util.ResponseUtils;
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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Host controller.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "마이버리", description = "마이버리 API")
public class HostController {

  private final JwtUtils jwtUtils;
  private final UserManager userManager;
  private final BucketlistManager bucketlistManager;
  private final CategoryManager categoryManager;
  private final NoticeRepository noticeRepository;
  private final SupportManager supportManager;
  private final HostService hostService;
  @Value("${mybury.address}")
  private String authServerAddress;

  /**
   * Sign up check host sign up check response vo.
   *
   * @param requestVO the request vo
   * @return the host sign up check response vo
   */
  @Operation(summary = "회원가입 여부 체크",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "회원가입 여부 체크 request",
      content = @Content(schema = @Schema(implementation = HostSignUpCheckRequestVO.class))))
  @PostMapping(value = "/signup_check")
  public HostSignUpCheckResponseVO signUpCheck(@RequestBody HostSignUpCheckRequestVO requestVO) {
    User user = userManager.getUserByEmail(requestVO.getEmail());
    boolean signUp = (user != null);

    log.info("???" + authServerAddress);
    return new HostSignUpCheckResponseVO(signUp, user);
  }

  /**
   * Sign up host sign up response vo.
   *
   * @param requestVO the request vo
   * @return the host sign up response vo
   */
  @Operation(summary = "회원가입",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "회원가입  request",
      content = @Content(schema = @Schema(implementation = HostSignUpRequestVO.class))))
  @PostMapping(value = "/signup")
  public HostSignUpResponseVO signUp(@RequestBody HostSignUpRequestVO requestVO) {
    User user = userManager.signup(requestVO);
    return new HostSignUpResponseVO(user);
  }

  /**
   * Sign in host sign in response vo.
   *
   * @param requestVO the request vo
   * @return the host sign in response vo
   */
  @Operation(summary = "로그인",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "로그인  request",
      content = @Content(schema = @Schema(implementation = HostSignInRequestVO.class))))
  @PostMapping(value = "/signin")
  public HostSignInResponseVO signIn(@RequestBody HostSignInRequestVO requestVO) {
    User user = userManager.signin(requestVO);
    String accessToken = jwtUtils.createAccessToken(user.getId());
    String refreshToken = jwtUtils.createRefreshToken(user.getId());
    return new HostSignInResponseVO(accessToken, refreshToken);
  }

  /**
   * Create profile base response vo.
   *
   * @param requestVO the request vo
   * @return the base response vo
   */
  @Operation(summary = "프로필 생성",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "프로필 생성  request",
      content = @Content(schema = @Schema(implementation = CreateProfileRequestVO.class))))
  @AccessTokenCheck
  @PostMapping(value = "/profile")
  public BaseResponseVO createProfile(@ModelAttribute CreateProfileRequestVO requestVO) {
    userManager.createProfile(requestVO);
    return BaseResponseVO.ok();
  }

  /**
   * Home home response vo.
   *
   * @param requestVO the request vo
   * @return the home response vo
   */
  @Operation(summary = "홈")
  //  @AccessTokenCheck
  @GetMapping(value = "/home")
  public HomeResponseVO home(@Parameter @ModelAttribute HomeRequestVO requestVO) {
    List<BucketlistResDTO> bucketlists = bucketlistManager.getBucketlistResDTO(requestVO);

    boolean popupYn = false;
    for (BucketlistResDTO bucketlistResDTO : bucketlists) {
      popupYn = bucketlistResDTO.getDDay() != null
        && (bucketlistResDTO.getDDay() == 1 || bucketlistResDTO.getDDay() == 7);
      if (popupYn)
        break;
    }
    return new HomeResponseVO(bucketlists, popupYn);
  }

  /**
   * D day d day response vo.
   *
   * @param requestVO the request vo
   * @return the d day response vo
   */
  @Operation(summary = "dday별 버킷리스트 조회",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "dday별 버킷리스트 조회 request",
      content = @Content(schema = @Schema(implementation = DDayRequestVO.class))))
  @AccessTokenCheck
  @GetMapping(value = "/dDay")
  public DDayResponseVO dDay(DDayRequestVO requestVO) {
    List<DDayResponseVO.DDayVO> dDayVOList = new ArrayList<>();
    List<Bucketlist> dDayBucketlists =
      bucketlistManager.getDDayBucketlist(requestVO.getUserId(), requestVO.getFilter());

    Map<Integer, List<Bucketlist>> map = dDayBucketlists
      .stream()
      .collect(Collectors.groupingBy(Bucketlist::getDDay));

    map.keySet().stream().forEach(v -> {
      DDayResponseVO.DDayVO dDayVO = new DDayResponseVO.DDayVO(v, map.get(v));
      dDayVOList.add(dDayVO);
    });

    return new DDayResponseVO(dDayVOList);
  }

  /**
   * Complete bucketlist base response vo.
   *
   * @param requestVO the request vo
   * @return the base response vo
   */
  @Operation(summary = "버킷리스트 완료",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "버킷리스트 완료 request",
      content = @Content(schema = @Schema(implementation = CompleteBucketlistRequestVO.class))))
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

  /**
   * Cancel bucketlist base response vo.
   *
   * @param requestVO the request vo
   * @return the base response vo
   */
  @Operation(summary = "버킷리스트 완료 취소",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "버킷리스트 완료 취소 request",
      content = @Content(schema = @Schema(implementation = CancelBucketlistRequestVO.class))))
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

  /**
   * Redo bucketlist base response vo.
   *
   * @param requestVO the request vo
   * @return the base response vo
   */
  @Operation(summary = "다시 도전하기",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "다시 도전하기 request",
      content = @Content(schema = @Schema(implementation = RedoBucketlistRequestVO.class))))
  @AccessTokenCheck
  @PostMapping(value = "/redo")
  public BaseResponseVO redoBucketlist(@RequestBody RedoBucketlistRequestVO requestVO) {
    Bucketlist bucketlist = this.bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
    bucketlist.setUserCount(0);
    bucketlist.setStatus("1");
    this.bucketlistManager.saveBucketlist(bucketlist);
    return BaseResponseVO.ok();
  }

  /**
   * Pin base response vo.
   *
   * @param requestVO the request vo
   * @return the base response vo
   */
  @Operation(summary = "고정",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "고정 request",
      content = @Content(schema = @Schema(implementation = PinBucketlistRequestVO.class))))
  @AccessTokenCheck
  @PostMapping(value = "/pin")
  public BaseResponseVO pin(@RequestBody PinBucketlistRequestVO requestVO) {
    Bucketlist bucketlist = bucketlistManager.getBucketlistById(requestVO.getBucketlistId());
    bucketlist.setPin(!bucketlist.isPin());
    bucketlistManager.saveBucketlist(bucketlist);
    return BaseResponseVO.ok();
  }

  /**
   * Before write before write response vo.
   *
   * @param userId the user id
   * @return the before write response vo
   */
  @Operation(summary = "사용자별 카테고리 정보")
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
   * @param requestVO the request vo
   * @return base response vo
   */
  @Operation(summary = "버킷리스트 등록",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "버킷리스트 등록 request",
      content = @Content(schema = @Schema(implementation = BucketlistWriteRequestVO.class))))
  @AccessTokenCheck
  @PostMapping(value = "/write")
  public BaseResponseVO writeBucketlist(@ModelAttribute BucketlistWriteRequestVO requestVO) {
    bucketlistManager.writeBucketlist(requestVO);
    return BaseResponseVO.ok();
  }

  /**
   * 버킷리스트 상세
   *
   * @param id     the id
   * @param userId the user id
   * @return bucketlist
   */
  @Operation(summary = "버킷리스트 상세")
  //  @AccessTokenCheck
  @GetMapping(value = "/bucketlist/{id}")
  public BucketlistViewResponseVO getBucketlist(@PathVariable String id, String userId) {
    Bucketlist bucketlist = bucketlistManager.getBucketlistById(id);
    return new BucketlistViewResponseVO(bucketlist);
  }

  /**
   * 버킷리스트 수정
   *
   * @param id        the id
   * @param requestVO the request vo
   * @return base response vo
   */
  @Operation(summary = "버킷리스트 수정",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "버킷리스트 수정 request",
      content = @Content(schema = @Schema(implementation = BucketlistModifyRequestVO.class))))
  @AccessTokenCheck
  @PostMapping(value = "/bucketlist/{id}")
  public BaseResponseVO modifyBucketlist(@PathVariable String id, BucketlistModifyRequestVO requestVO) {
    bucketlistManager.modifyBucketlist(requestVO);
    return BaseResponseVO.ok();
  }

  /**
   * Remove bucketlist base response vo.
   *
   * @param id        the id
   * @param requestVO the request vo
   * @return the base response vo
   */
  @Operation(summary = "버킷리스트 삭제",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "버킷리스트 삭제 request",
      content = @Content(schema = @Schema(implementation = BucketlistRemoveRequestVO.class))))
  @AccessTokenCheck
  @DeleteMapping(value = "/bucketlist/{id}")
  public BaseResponseVO removeBucketlist(@PathVariable String id, @RequestBody BucketlistRemoveRequestVO requestVO) {
    bucketlistManager.deleteBucketlist(id);
    return BaseResponseVO.ok();
  }

  /**
   * Mypage my page response vo.
   *
   * @param requstVO the requst vo
   * @return the my page response vo
   */
  @Operation(summary = "마이페이지",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "마이페이지 request",
      content = @Content(schema = @Schema(implementation = MyPageRequestVO.class))))
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
    List<Category> userCategoryList = user.getCategoryList();
    for (Category category : userCategoryList) {
      long categoryCount =
        user.getBucketlists().stream()
          .filter(b -> b.getCategory().getId().equals(category.getId()))
          .count();

      if (!(categoryCount == 0 && category.getName().equals("없음"))) {
        MyPageResponseVO.CategoryVO categoryVO =
          new MyPageResponseVO.CategoryVO(category.getId(), category.getName(), Long.valueOf(categoryCount).intValue());
        categoryList.add(categoryVO);
      }
    }
    return categoryList;
  }

  /**
   * Gets category.
   *
   * @param requestVO the request vo
   * @return the category
   */
  @Operation(summary = "카테고리별 버킷리스트 목록 조회",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "카테고리별 버킷리스트 목록 조회 request",
      content = @Content(schema = @Schema(implementation = CategoryRequestVO.class))))
  //  @AccessTokenCheck
  @GetMapping(value = "/category")
  public CategoryResponseVO getCategory(CategoryRequestVO requestVO) {
    List<Bucketlist> bucketlists = bucketlistManager.getBucketlistByCategoryId(requestVO.getCategoryId());
    return new CategoryResponseVO(bucketlists);
  }

  /**
   * Create category base response vo.
   *
   * @param requestVO the request vo
   * @return the base response vo
   */
  @Operation(summary = "카테고리 추가",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "카테고리 추가 request",
      content = @Content(schema = @Schema(implementation = CreateCategoryRequestVO.class))))
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

  /**
   * Remove category base response vo.
   *
   * @param requestVO the request vo
   * @return the base response vo
   */
  @Operation(summary = "카테고리 삭제",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "카테고리 삭제 request",
      content = @Content(schema = @Schema(implementation = RemoveCategoryRequestVO.class))))
  @AccessTokenCheck
  @DeleteMapping(value = "/category")
  public BaseResponseVO removeCategory(@RequestBody RemoveCategoryRequestVO requestVO) {
    categoryManager.remove(requestVO);
    return BaseResponseVO.ok();
  }

  /**
   * Modify category name base response vo.
   *
   * @param requestVO the request vo
   * @return the base response vo
   */
  @Operation(summary = "카테고리명 변경",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "카테고리명 변경 request",
      content = @Content(schema = @Schema(implementation = ModifyCategoryNameRequestVO.class))))
  @AccessTokenCheck
  @PostMapping(value = "/category/edit_name")
  public BaseResponseVO modifyCategoryName(@RequestBody ModifyCategoryNameRequestVO requestVO) {
    categoryManager.modifyName(requestVO);
    return BaseResponseVO.ok();
  }

  /**
   * Modify category priority base response vo.
   *
   * @param requestVO the request vo
   * @return the base response vo
   */
  @Operation(summary = "카테고리 순서 변경",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "카테고리 순서 변경 request",
      content = @Content(schema = @Schema(implementation = ModifyCategoryPriorityRequestVO.class))))
  @AccessTokenCheck
  @PostMapping(value = "/category/edit_priority")
  public BaseResponseVO modifyCategoryPriority(@RequestBody ModifyCategoryPriorityRequestVO requestVO) {
    categoryManager.modifyPriority(requestVO);
    return BaseResponseVO.ok();
  }

  /**
   * Withdrawal base response vo.
   *
   * @param requestVO the request vo
   * @return the base response vo
   */
  @Operation(summary = "회원탈퇴",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "회원탈퇴 request",
      content = @Content(schema = @Schema(implementation = WithdrawalRequestVO.class))))
  @AccessTokenCheck
  @DeleteMapping(value = "/withdrawal")
  public BaseResponseVO withdrawal(@RequestBody WithdrawalRequestVO requestVO) {
    userManager.remove(requestVO.getUserId());
    return BaseResponseVO.ok();
  }

  /**
   * 후원
   *
   * @param requestVO the request vo
   * @return base response vo
   */
  @Operation(summary = "후원하기",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "후원하기 request",
      content = @Content(schema = @Schema(implementation = SupportHistoryRequestVO.class))))
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
   * @param requestVO the request vo
   * @return support items response vo
   */
  @Operation(summary = "후원 아이템 리스트",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "후원 아이템 리스트 request",
      content = @Content(schema = @Schema(implementation = SupportItemRequestVO.class))))
  @PostMapping(value = "/support_items")
  public SupportItemsResponseVO supportItems(@RequestBody SupportItemRequestVO requestVO) {
    SupportItemsResponseVO vo = new SupportItemsResponseVO(hostService.findAllSupportItem());

    // 성공여부가 N인 후원이력을 같이 전달한다.
    List<SupportHistory> supportHistory =
      supportManager.findByUserIdAndSusYnOrderByCreatedDtDesc(requestVO.getUserId(), "N".charAt(0));
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
   * @param requestVO the request vo
   * @return base response vo
   */
  @Operation(summary = "후원 아이템 토큰 및 성공여부 업데이트",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "후원 아이템 토큰 및 성공여부 업데이트 request",
      content = @Content(schema = @Schema(implementation = SupportHistoryRequestVO.class))))
  @AccessTokenCheck
  @PostMapping(value = "/support_edit")
  public BaseResponseVO supportEdit(@RequestBody SupportHistoryRequestVO requestVO) {

    SupportHistory supportHistory = supportManager.findOneByUserIdAndToken(requestVO.getUserId(), requestVO.getToken());

    if (supportHistory != null && !requestVO.getSusYn().equals(supportHistory.getSusYn().toString())) {
      supportHistory.setSusYn(requestVO.getSusYn().charAt(0));
      supportManager.updateSupportHistory(supportHistory);
    }

    return BaseResponseVO.ok();
  }

  /**
   * 버킷리스트 순서 변경
   *
   * @param requestVO the request vo
   * @return the base response vo
   */
  @Operation(summary = "버킷리스트 순서 변경",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "버킷리스트 순서 변경 request",
      required = true,
      content = @Content(schema = @Schema(implementation = ChangeOrderListDTO.class))))
  @AccessTokenCheck
  @PostMapping("/change_order")
  public BaseResponseVO changeOrder(@RequestBody ChangeOrderListDTO requestVO) {
    hostService.changeOrder(requestVO);
    return BaseResponseVO.ok();
  }

  @Operation(summary = "버킷리스트 검색",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "버킷리스트 검색 request",
      required = true, content = @Content(schema = @Schema(implementation = SearchRequestDTO.class))),
    responses = {
      @ApiResponse(responseCode = "200", description = "검색", content = @Content(schema =
      @Schema(implementation = SearchResDTO.class)))})
  //  @AccessTokenCheck
  @PostMapping("/search")
  public ResponseEntity<Object> search(@RequestBody SearchRequestDTO request) {
    return ResponseUtils.success(hostService.searchBucketlist(request));
  }

  /**
   * 후원 아이템 수정
   *
   * @param param the param
   * @return base response vo
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
   *
   * @return notice
   */
  @Operation(summary = "팝업 정보")
  @AccessTokenCheck
  @GetMapping(value = "/notice")
  public BaseResponseVO getNotice() {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String today = sdf.format(new Date());

    List<Notice> notice = noticeRepository.findByStartDtLessThanEqualAndEndDtGreaterThanEqualAndDpYn(today, today,
      "Y".charAt(0));

    NoticeResponseVO vo = new NoticeResponseVO(notice);

    return vo;
  }

  /**
   * 공지사항 추가
   *
   * @param requestVO the request vo
   * @return base response vo
   */
  @PostMapping(value = "/save_notice")
  public BaseResponseVO saveNotice(@RequestBody NoticeRequestVO requestVO) {

    Notice notice = Notice.builder()
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
