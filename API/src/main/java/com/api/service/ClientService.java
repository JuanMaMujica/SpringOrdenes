package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.Client;
import com.api.repositories.IClientRepository;

@Service
public class ClientService implements IClientService {

	@Autowired
	private IClientRepository cR;
	
	@Override
	public List<Client> getAll() {
		return cR.findAll();
	}

	@Override
	public boolean save(Client c) {
		if(cR.existsById(c.getId()))
			return false;
		else {
			cR.save(c);
			return true;
		}
	}

	@Override
	public boolean delete(long id) {
		if(cR.existsById(id)) {	
			cR.deleteById(id);
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean update(Client c) {
		if(cR.existsById(c.getId())) {	
			cR.save(c);
			return true;
		}
		else
			return false;
	}
	
}
