package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/signup")
public class SignUpController {

	private UserService userService;

	public SignUpController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public String loadPageSignUp() {
		return "signup";
	}

	@PostMapping
	public String signup(User user, Model model, RedirectAttributes redirectAttributes) {
		String errMessage = "";

		// Check userName exist in system
		if (userService.findUserByUsername(user.getUsername()) != null) {
			errMessage = "Sorry, username already exists, please enter another username!";
			model.addAttribute("signupError", errMessage);
			return "signup";
		}

		// SignUp userName in system
		if (userService.insert(user) == 0) {
			errMessage = "A system error has occurred, please try again!";
			model.addAttribute("signupError", errMessage);
			return "signup";
		}

		redirectAttributes.addFlashAttribute("signupSuccess", true);
		return "redirect:/login";
	}
}
