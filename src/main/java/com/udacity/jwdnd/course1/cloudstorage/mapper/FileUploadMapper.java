package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import com.udacity.jwdnd.course1.cloudstorage.model.FileUpload;

@Mapper
public interface FileUploadMapper {
	// get all files
	final String FIND_ALL_FILE = "SELECT * FROM FILES WHERE userid = #{userId}";

	@Select(FIND_ALL_FILE)
	List<FileUpload> findAll(Integer userId);

	@Select("SELECT * FROM FILES WHERE filename = #{fileName} AND userid = #{userId}")
	FileUpload findByName(String fileName, Integer userId);

	@Select("SELECT * FROM FILES WHERE fileId = #{fileId} AND userid = #{userId}")
	FileUpload findById(Integer fileId, Integer userId);

	@Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
	@Options(useGeneratedKeys = true, keyProperty = "fileId")
	int insert(FileUpload file);

	@Delete("DELETE FROM FILES WHERE fileId = #{fileId} AND userid = #{userId}")
	int deleteById(Integer fileId, Integer userId);
}
