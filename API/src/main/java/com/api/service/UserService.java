package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.User;
import com.api.repositories.IUserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository uR;
	
	@Override
	public List<User> getAll() {
		return uR.findAll();
	}

	@Override
	public boolean save(User u) {
		if(uR.existsById(u.getId()))
			return false;
		else {
			uR.save(u);
			return true;
		}
	}

	@Override
	public boolean delete(long id) {
		if(uR.existsById(id)) {	
			uR.deleteById(id);
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean update(User u) {
		if(uR.existsById(u.getId())) {	
			uR.save(u);
			return true;
		}
		else
			return false;
	}
	
}
