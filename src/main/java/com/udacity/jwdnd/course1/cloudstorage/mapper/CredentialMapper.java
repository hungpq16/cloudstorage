package com.udacity.jwdnd.course1.cloudstorage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;

@Mapper
public interface CredentialMapper {
	// get all credentials
	final String FIND_ALL_CREDENTIALS = "SELECT * FROM CREDENTIALS WHERE userid = #{userId}";

	@Select(FIND_ALL_CREDENTIALS)
	List<Credential> findAll(Integer userId);

	@Select("SELECT * FROM CREDENTIALS WHERE credentialid = #{credentialId} and userid = #{userId}")
	Credential findById(Integer credentialId, Integer userId);

	@Select("SELECT * FROM CREDENTIALS WHERE url = #{url} and username = #{username}")
	Credential findByUrlAndUsername(String url, String username);

	@Insert("INSERT INTO CREDENTIALS (url, username, key, password, userid) VALUES (#{url}, #{username}, #{key}, #{password}, #{userId})")
	@Options(useGeneratedKeys = true, keyProperty = "credentialId")
	int insert(Credential credential);

	@Update("UPDATE CREDENTIALS SET url = #{url}, username = #{username}, key = #{key}, password = #{password} WHERE credentialid = #{credentialId} and userid = #{userId}")
	int updateById(Credential credential);

	@Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId} and userid = #{userId}")
	int deleteById(Integer credentialId, Integer userId);
}
