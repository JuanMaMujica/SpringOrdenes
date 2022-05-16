package com.api.service;

import java.util.List;

import com.api.model.User;

public interface IUserService {
	List<User> getAll();
	boolean save(User u);
	boolean delete(long id);
	boolean update(User u);
}
