package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;

@Controller
@RequestMapping("/home")
public class HomeController {

	private NoteService noteService;
	private CredentialService credentialService;
	private FileUploadService fileUploadService;

	public HomeController(NoteService noteService, CredentialService credentialService,
			FileUploadService fileUploadService) {
		super();
		this.noteService = noteService;
		this.credentialService = credentialService;
		this.fileUploadService = fileUploadService;
	}

	@GetMapping()
	public String index(Authentication authentication, Model model) {
		// Get userId
		User user = (User) authentication.getPrincipal();
		Integer userId = user.getUserId();

		// Get all information display on home page
		model.addAttribute("listFileUpload", fileUploadService.findAll(userId));
		model.addAttribute("listNote", noteService.findAll(userId));
		model.addAttribute("listCredentials", credentialService.findAll(userId));
		return "home";
	}
}
