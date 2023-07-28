package com.udacity.jwdnd.course1.cloudstorage.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.impl.IBaseAction;

@Service
public class NoteService implements IBaseAction<Note> {

	private final NoteMapper noteMapper;

	public NoteService(NoteMapper noteMapper) {
		this.noteMapper = noteMapper;
	}

	@Override
	public List<Note> findAll(Integer userId) {
		return noteMapper.findAll(userId);
	}

	@Override
	public Note findById(Integer noteId, Integer userId) {
		return noteMapper.findById(noteId, userId);
	}

	@Override
	public int insert(Note note) {
		return noteMapper.insert(note);
	}

	@Override
	public int updateById(Note note) {
		return noteMapper.updateById(note);
	}

	@Override
	public int deleteById(Integer noteId, Integer userId) {
		return noteMapper.deleteById(noteId, userId);
	}

}
