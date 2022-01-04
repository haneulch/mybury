package com.mybury.bucketlist.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mybury.bucketlist.auth.constants.ApiUriConstants;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = ApiUriConstants.PRIVACY_POLICY)
	public String privacyPolicy() {
		return "privacy_policy";
	}

	@RequestMapping(value = ApiUriConstants.TERMS_OF_USE)
	public String termsOfUse() {
		return "terms_of_use";
	}
}
