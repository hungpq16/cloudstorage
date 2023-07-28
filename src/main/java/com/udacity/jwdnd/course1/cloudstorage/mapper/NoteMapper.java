package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;

@Mapper
public interface NoteMapper {
	// get all notes
	final String FIND_ALL_NOTE = "SELECT * FROM NOTES WHERE userid = #{userId}";
	
	// get note by id
	final String FIND_NOTE_BY_ID = "SELECT * FROM NOTES WHERE noteid = #{noteId} and userid = #{userId}";

	// get note by full information, check duplicate
	final String FIND_NOTE_BY_TITLE_AND_DESCRIPTION = "SELECT * FROM NOTES WHERE notetitle = #{noteTitle} and notedescription = #{noteDescription}";

	// insert note
	final String INSERT_NOTE = "INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{noteTitle}, #{noteDescription}, #{userId})";
	
	// update note by id
	final String UPDATE_NOTE_BY_ID = "UPDATE NOTES SET notetitle = #{noteTitle}, notedescription = #{noteDescription} WHERE noteid = #{noteId} and userid = #{userId}";
	
	// delete note by id
	final String DELETE_NOTE_BY_ID = "DELETE FROM NOTES WHERE noteid = #{noteId} and userid = #{userId}";
	
	@Select(FIND_ALL_NOTE)
	List<Note> findAll(Integer userId);

	@Select(FIND_NOTE_BY_ID)
	Note findById(Integer noteId, Integer userId);

	@Select(FIND_NOTE_BY_TITLE_AND_DESCRIPTION)
	Note findByTittelAndDescription(String noteTitle, String noteDescription);

	@Insert(INSERT_NOTE)
	@Options(useGeneratedKeys = true, keyProperty = "noteId")
	int insert(Note note);

	@Update(UPDATE_NOTE_BY_ID)
	int updateById(Note note);

	@Delete(DELETE_NOTE_BY_ID)
	int deleteById(Integer noteId, Integer userId);
}
