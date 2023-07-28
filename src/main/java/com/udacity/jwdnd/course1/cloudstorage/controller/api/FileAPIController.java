package com.udacity.jwdnd.course1.cloudstorage.controller.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udacity.jwdnd.course1.cloudstorage.model.FileUpload;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;

@RestController
public class FileAPIController {

	private FileUploadService fileUploadService;

	public FileAPIController(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	@GetMapping("/fileUpload/view")
	public ResponseEntity<byte[]> handleViewFile(Integer fileId, Authentication authentication) {
		Integer userId = ((User) authentication.getPrincipal()).getUserId();
		FileUpload file = fileUploadService.findById(fileId, userId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", file.getFileName());
		headers.setContentType(MediaType.parseMediaType(file.getContentType()));
		return new ResponseEntity<>(file.getFileData(), headers, HttpStatus.OK);
	}

}
