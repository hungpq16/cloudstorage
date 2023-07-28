package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.impl.IBaseAction;

@Service
public class CredentialService implements IBaseAction<Credential> {

	private final CredentialMapper credentialMapper;
	private final EncryptionService encryptionService;

	public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
		super();
		this.credentialMapper = credentialMapper;
		this.encryptionService = encryptionService;
	}

	@Override
	public List<Credential> findAll(Integer userId) {
		return credentialMapper.findAll(userId);
	}

	@Override
	public Credential findById(Integer credentialId, Integer userId) {
		return credentialMapper.findById(credentialId, userId);
	}

	@Override
	public int insert(Credential credential) {
		encryptPassword(credential);
		return credentialMapper.insert(credential);
	}

	@Override
	public int updateById(Credential credential) {
		encryptPassword(credential);
		return credentialMapper.updateById(credential);
	}

	@Override
	public int deleteById(Integer credentialId, Integer userId) {
		return credentialMapper.deleteById(credentialId, userId);
	}

	public Credential findByUrlAndUsername(String url, String username) {
		return credentialMapper.findByUrlAndUsername(url, username);
	}

	private void encryptPassword(Credential credential) {
		SecureRandom random = new SecureRandom();
		byte[] key = new byte[16];
		random.nextBytes(key);
		String encodedKey = Base64.getEncoder().encodeToString(key);
		String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
		credential.setPassword(encryptedPassword);
		credential.setKey(encodedKey);
	}

	public void decryptPassword(Credential credential) {
		if (credential.getKey() == null) {
			return;
		}
		String decryptedPassword = encryptionService.decryptValue(credential.getPassword(), credential.getKey());
		credential.setPassword(decryptedPassword);
	}

}
