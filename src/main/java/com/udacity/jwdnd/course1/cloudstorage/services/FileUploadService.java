package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileUploadMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.FileUpload;
import com.udacity.jwdnd.course1.cloudstorage.services.impl.IBaseAction;

@Service
public class FileUploadService implements IBaseAction<FileUpload> {

	private FileUploadMapper fileUploadMapper;

	public FileUploadService(FileUploadMapper fileUploadMapper) {
		this.fileUploadMapper = fileUploadMapper;
	}

	@Override
	public List<FileUpload> findAll(Integer userId) {
		return fileUploadMapper.findAll(userId);
	}

	@Override
	public FileUpload findById(Integer fileId, Integer userId) {
		return fileUploadMapper.findById(fileId, userId);
	}

	@Override
	public int insert(FileUpload obj) {
		return 0;
	}

	@Override
	public int updateById(FileUpload obj) {
		return 0;
	}

	@Override
	public int deleteById(Integer fileId, Integer userId) {
		return fileUploadMapper.deleteById(fileId, userId);
	}

	public FileUpload findByName(MultipartFile file, Integer userId) {
		return fileUploadMapper.findByName(this.handleFileName(file), userId);
	}

	public int saveFile(MultipartFile file, Integer userId) throws IOException {
		FileUpload fileUpload = new FileUpload();
		fileUpload.setFileName(this.handleFileName(file));
		fileUpload.setContentType(file.getContentType());
		fileUpload.setFileSize(String.valueOf(file.getSize()));
		fileUpload.setFileData(file.getBytes());
		fileUpload.setUserId(userId);
		return fileUploadMapper.insert(fileUpload);
	}

	private String handleFileName(MultipartFile fileUpload) {
		String fileName = fileUpload.getOriginalFilename();
		int startIndex = fileName.replaceAll("\\\\", "/").lastIndexOf("/");
		return fileName.substring(startIndex + 1);
	}

}
