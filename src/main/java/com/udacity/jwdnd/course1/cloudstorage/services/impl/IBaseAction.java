package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import java.util.List;

public interface IBaseAction<T> {
	List<T> findAll(Integer id);

	T findById(Integer id, Integer userId);

	int insert(T obj);

	int updateById(T obj);

	int deleteById(Integer id, Integer userId);

}
