package com.mybury.bucketlist.auth.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Hidden
@Controller
public class HomeController {
	@RequestMapping(value = "/privacy_policy")
	public String privacyPolicy() {
		return "privacy_policy";
	}

	@RequestMapping(value = "/terms_of_use")
	public String termsOfUse() {
		return "terms_of_use";
	}
}
