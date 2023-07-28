package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import com.udacity.jwdnd.course1.cloudstorage.model.FileUpload;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;

@Controller
@ControllerAdvice
@RequestMapping("/fileUpload")
public class FileUploadController {

	// final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB

	@Value("${spring.servlet.multipart.max-file-size}")
	private String maxFileSize;

	private FileUploadService fileUploadService;

	public FileUploadController(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	@PostMapping("/upload")
	public String handleFileUpload(MultipartFile fileUpload, Authentication authentication,
			RedirectAttributes redirectAttributes) throws IOException, SizeLimitExceededException {
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

	/**
	 * Method check upload file have size > 10MB
	 * 
	 * @param redirectAttributes
	 * @return home page
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleFileUploadError(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("errMessage", "File must not exceed " + this.maxFileSize + ", please try again!");
		return "redirect:/result?error";
	}

}
