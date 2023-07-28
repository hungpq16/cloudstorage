package com.udacity.jwdnd.course1.cloudstorage.model;

public class FileUpload {
	private Integer fileId;
	private byte[] fileData;
	private String fileName;
	private String contentType;
	private String fileSize;
	private Integer userId;

	public FileUpload() {
		super();
	}

	public FileUpload(Integer fileId, byte[] fileData, String fileName, String contentType, String fileSize,
			Integer userId) {
		super();
		this.fileId = fileId;
		this.fileData = fileData;
		this.fileName = fileName;
		this.contentType = contentType;
		this.fileSize = fileSize;
		this.userId = userId;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
