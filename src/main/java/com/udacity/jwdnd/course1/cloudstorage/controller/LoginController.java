package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.udacity.jwdnd.course1.cloudstorage.services.impl.IAuthenticationFacade;

@Controller
public class LoginController {

	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@GetMapping("/login")
	public String login() {
		// Get information of user, in the case logged will redirect to home page
		Authentication authentication = authenticationFacade.getAuthentication();
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		return "home";
	}

}
