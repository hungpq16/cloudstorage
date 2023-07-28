package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;

@Controller
@RequestMapping("/credential")
public class CredentialController {

	// private static final String TAB_CREDENTIAL = "credentials";
	private final CredentialService credentialService;

	public CredentialController(CredentialService credentialService) {
		this.credentialService = credentialService;
	}

	@ResponseBody
	@GetMapping("/view")
	public Credential getCredential(Integer credentialId, Authentication authentication) {
		Integer userId = ((User) authentication.getPrincipal()).getUserId();
		Credential credential = credentialService.findById(credentialId, userId);
		if (credential == null) {
			return null;
		}
		credentialService.decryptPassword(credential);
		return credential;
	}

	@PostMapping("/save")
	public String insertCredential(Credential credential, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		Integer userId = ((User) authentication.getPrincipal()).getUserId();
		Integer credentialId = credential.getCredentialId();
		credential.setUserId(userId);

		//redirectAttributes.addFlashAttribute("url", TAB_CREDENTIAL);

		if (credentialId == null) {
			if (credentialService.findByUrlAndUsername(credential.getUrl(), credential.getUsername()) != null) {
				redirectAttributes.addFlashAttribute("errMessage", "The Credential is exist, please try again!");
				return "redirect:/result?error";
			}
			if (credentialService.insert(credential) == 0) {
				redirectAttributes.addFlashAttribute("errMessage", "Add the Credential failed, please try again!");
				return "redirect:/result?error";
			}
			// redirectAttributes.addFlashAttribute("successMessage", "Add the Credential success!");
		} else {
			if (credentialService.findById(credentialId, userId) != null) {
				if (credentialService.updateById(credential) == 0) {
					redirectAttributes.addFlashAttribute("errMessage", "Update the Credential failed, please try again!");
					return "redirect:/result?error";
				}
				// redirectAttributes.addFlashAttribute("successMessage", "Update the Credential success!");
			}
		}
		return "redirect:/result?success";
	}

	@GetMapping("/delete")
	public String deleteCredential(Authentication authentication, RedirectAttributes redirectAttributes,
			Integer credentialId) {
		Integer userId = ((User) authentication.getPrincipal()).getUserId();

		//redirectAttributes.addFlashAttribute("url", TAB_CREDENTIAL);

		if (credentialService.deleteById(credentialId, userId) == 0) {
			redirectAttributes.addFlashAttribute("errMessage", "Delete the Credential failed, please try again!");
			return "redirect:/result?error";
		}
		return "redirect:/result?success";
	}

}
