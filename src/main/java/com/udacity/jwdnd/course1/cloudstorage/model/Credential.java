package com.udacity.jwdnd.course1.cloudstorage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Credential {
	@JsonIgnore
	private String key;
	private String password;
	private Integer credentialId;
	private String url;
	private String username;
	private Integer userId;

	public Credential() {
		super();
	}

	public Credential(String key, String password, Integer credentialId, String url, String username, Integer userId) {
		super();
		this.key = key;
		this.password = password;
		this.credentialId = credentialId;
		this.url = url;
		this.username = username;
		this.userId = userId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(Integer credentialId) {
		this.credentialId = credentialId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
