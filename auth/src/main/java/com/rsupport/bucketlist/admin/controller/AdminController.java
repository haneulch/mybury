package com.rsupport.bucketlist.admin.controller;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rsupport.bucketlist.core.common.OtpUtils;
import com.rsupport.bucketlist.core.domain.AdminUser;
import com.rsupport.bucketlist.core.service.AdminUserManager;
import com.rsupport.bucketlist.core.service.SupportManager;
import com.rsupport.bucketlist.core.service.UserManager;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private SupportManager supportManager;
	
	private UserManager userManager;
	
	private AdminUserManager adminUserManager;
	
	private AdminController(SupportManager supportManager, UserManager userManager, AdminUserManager adminUserManager) {
		this.supportManager = supportManager;
		this.userManager = userManager;
		this.adminUserManager = adminUserManager;
	}
	
	@GetMapping("")
	public String adminLogin() { return "/admin/login";	}
	
	@PostMapping("/otpCheck")
	public String adminOtpCheck( HttpServletRequest request 
			, @RequestParam Map<String, Object> param) {
		
		String username = param.getOrDefault("userName", "") + "";
		String error = "?";
		
		if( !username.equals("")) {
			HttpSession session = request.getSession();
			
			AdminUser admin = adminUserManager.findOneByUsername( username);
			
			if( admin == null) {
				// user not exist
				error += "code=1";
			} else {
				String otpValue = param.getOrDefault("otpValue", "") + "";
				
				if( OtpUtils.checkCode(admin.getOtpKey(), otpValue)) {
					
					session.setMaxInactiveInterval(1800);
					session.setAttribute("isAdmin", true);
					
					admin.setLastLoginDt(new Date());
					adminUserManager.save(admin);
					
					return "redirect:/admin/page/main";
				} else {
					// code invalid
					session.invalidate();
					error += "code=2";
				}
			}
		}
		return "redirect:/admin" + error;
	}
	
	@GetMapping("/logout")
	public String adminLogout( HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/admin/login";
	}
	
	@GetMapping("/page/{page}")
	public String pageMapping( @PathVariable String page) {
		return "/admin/" + page;
	}
	
	/**
	 * 후원 아이템 관리
	 * @param m
	 * @return
	 */
	@PostMapping("/json/findSupportItem")
	public ResponseEntity<Object> findSupportItem(
			@RequestBody(required=false) Map<String, Object> m) {
		return new ResponseEntity<Object>(supportManager.getSupportItems(), HttpStatus.OK);
	}
	
	/**
	 * 결제관리
	 * @param m
	 * @return
	 */
	@PostMapping("/json/findSupportHistory")
	public ResponseEntity<Object> findSupportHistory(
			@RequestBody(required=false) Map<String, Object> m) {
		
		Pageable page = PageRequest.of( Integer.parseInt(m.getOrDefault("page", "0") + ""), 20);
		String search = m.getOrDefault("search", "") + "";
		
		return new ResponseEntity<Object>(supportManager.getSupportHistory(search, page), HttpStatus.OK);
	}
	
	/**
	 * 사용자 관리
	 * @param m
	 * @return
	 */
	@PostMapping("/json/findUser")
	public ResponseEntity<Object> findUser(
			@RequestBody(required=false) Map<String, Object> m) {
		
		Pageable page = PageRequest.of( Integer.parseInt(m.getOrDefault("page", "0") + ""), 20);
		String search = m.getOrDefault("search", "") + "";
		
		return new ResponseEntity<Object>(userManager.getUser(search, page), HttpStatus.OK);
	}
	
	/**
	 * 사용자 관리 > 상세
	 * @param m
	 * @return
	 */
	@PostMapping("/json/findOneUser")
	public ResponseEntity<Object> findOneUser(
			@RequestBody(required=false) Map<String, Object> m) {
		String userId = m.getOrDefault("userId", "") + "";
		
		m.put("user", userManager.getUserMappingById(userId));
		m.put("history", supportManager.getSupportHistoryByUserId(userId));
		
		return new ResponseEntity<Object>(m, HttpStatus.OK);
	}
	
	/**
	 * 결제관리 > 
	 * @param m
	 * @return
	 */
	@PostMapping("/json/updateSusYn")
	public ResponseEntity<Object> updateSusYn(
			@RequestBody(required=false) Map<String, Object> m) {
		int seq = Integer.parseInt(m.getOrDefault("seq", "0") + "");
		String susYn = m.getOrDefault("susYn", "") + "";
		
		if( susYn.equals("")) {
			return new ResponseEntity<Object>(m, HttpStatus.BAD_REQUEST);
		} else {
			supportManager.updateSusYn( seq, susYn);
		}
		
		return new ResponseEntity<Object>(m, HttpStatus.OK);
	}
	
	/**
	 * 어드민 계정 관리
	 * @param m
	 * @return
	 */
	@PostMapping("/json/findAdminUser")
	public ResponseEntity<Object> findAdminUser(
			@RequestBody(required=false) Map<String, Object> m) {
		
		Pageable page = PageRequest.of( Integer.parseInt(m.getOrDefault("page", "0") + ""), 20);
		String search = m.getOrDefault("search", "") + "";
		
		return new ResponseEntity<Object>(adminUserManager.findByUsername(search, page), HttpStatus.OK);
	}
	
	/**
	 * 어드민 계정 관리 > otp 초기화
	 * @param m
	 * @return
	 */
	@PostMapping("/json/initializeOtpKey")
	public ResponseEntity<Object> initializeOtpKey(
			@RequestBody(required=false) Map<String, Object> m) {
		
		String username = m.getOrDefault("username", "") + "";
		if( username.equals("")) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		
		m.put("otpKey", adminUserManager.initializeOtpKey(username));
		
		return new ResponseEntity<Object>(m, HttpStatus.OK);
	}
	
	/**
	 * 어드민 계정 관리 > 계정 삭제
	 * @param m
	 * @return
	 */
	@PostMapping("/json/deleteAdminUser")
	public ResponseEntity<Object> deleteAdminUser(
			@RequestBody(required=false) Map<String, Object> m) {
		
		String username = m.getOrDefault("username", "") + "";
		if( username.equals("")) {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
		
		adminUserManager.deleteById(username);
		
		return new ResponseEntity<Object>(null, HttpStatus.OK);
	}
}
