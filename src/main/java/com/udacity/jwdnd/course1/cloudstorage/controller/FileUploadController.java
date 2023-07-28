package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import com.udacity.jwdnd.course1.cloudstorage.model.FileUpload;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

	private FileUploadService fileUploadService;

	public FileUploadController(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	@PostMapping("/upload")
	public String handleFileUpload(MultipartFile fileUpload, Authentication authentication,
			RedirectAttributes redirectAttributes) throws IOException {
		Integer userId = ((User) authentication.getPrincipal()).getUserId();
		String errMessage = "";

		if (fileUpload.isEmpty()) {
			errMessage = "File is empty, please rechose file!";
		}

		FileUpload existFile = fileUploadService.findByName(fileUpload, userId);
		if (existFile != null) {
			errMessage = "File is already exist!, please other chose file!";
		}

		if (!StringUtils.isEmpty(errMessage)) {
			redirectAttributes.addFlashAttribute("errMessage", errMessage);
			return "redirect:/result?error";
		}

		if (fileUploadService.saveFile(fileUpload, userId) == 0) {
			redirectAttributes.addFlashAttribute("errMessage", "Upload file failed, please try again!");
			return "redirect:/result?error";
		}

		redirectAttributes.addFlashAttribute("successMessage", "Upload file success!");
		return "redirect:/result?success";
	}

	@GetMapping("/delete")
	public String handleDeleteFile(Integer fileId, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		Integer userId = ((User) authentication.getPrincipal()).getUserId();
		if (fileUploadService.deleteById(fileId, userId) == 0) {
			redirectAttributes.addFlashAttribute("errMessage", "Not exist file, please try again!");
			return "redirect:/result?error";
		}
		return "redirect:/result?success";
	}

}
