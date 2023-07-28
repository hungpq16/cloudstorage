package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController {

	private NoteService noteService;

	public NoteController(NoteService noteService) {
		super();
		this.noteService = noteService;
	}

	@PostMapping("/save")
	public String saveNote(Authentication authentication, RedirectAttributes redirectAttributes, Note note) {
		Integer userId = ((User) authentication.getPrincipal()).getUserId();
		Integer noteId = note.getNoteId();
		note.setUserId(userId);

		// In the case insert note
		if (noteId == null) {
			if (noteService.findByTittelAndDescription(note.getNoteTitle(), note.getNoteDescription()) != null) {
				redirectAttributes.addFlashAttribute("errMessage", "The Note is exist, please try again!");
				return "redirect:/result?error";
			}
			if (noteService.insert(note) == 0 ) {
				redirectAttributes.addFlashAttribute("errMessage", "Add the Note failed, please try again!");
				return "redirect:/result?error";
			}
			// redirectAttributes.addFlashAttribute("successMessage", "Add the Note success!");
		} else {
			// In the case update note
			if (noteService.findById(noteId, userId) != null) {
				if (noteService.updateById(note) == 0) {
					redirectAttributes.addFlashAttribute("errMessage", "Update the Note failed, please try again!");
					return "redirect:/result?error";
				}
				// redirectAttributes.addFlashAttribute("successMessage", "Update the Note success!");
			}
		}
		return "redirect:/result?success";
	}

	@GetMapping("/delete")
	public String deleteNote(Authentication authentication, RedirectAttributes redirectAttributes,
			Integer noteId) {
		Integer userId = ((User) authentication.getPrincipal()).getUserId();

		if (noteService.deleteById(noteId, userId) == 0) {
			redirectAttributes.addFlashAttribute("errMessage", "Remove the Note failed, please try again!");
			return "redirect:/result?error";
		}
		// redirectAttributes.addFlashAttribute("successMessage", "Remove the Note success");
		return "redirect:/result?success";
	}

}
