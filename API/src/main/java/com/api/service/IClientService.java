package com.api.service;

import java.util.List;

import com.api.model.Client;

public interface IClientService {
	List<Client> getAll();
	boolean save(Client c);
	boolean delete(long id);
	boolean update(Client c);
}
