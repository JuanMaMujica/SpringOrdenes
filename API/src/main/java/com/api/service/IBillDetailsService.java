package com.api.service;

import java.util.List;
import java.util.Optional;

import com.api.model.BillDetails;

public interface IBillDetailsService {
	
	Optional<BillDetails> get(long id);
	boolean save(BillDetails listBd);
	boolean delete(long id);
	boolean update(BillDetails bd);
	List<BillDetails> getAllByBillNumber(long billNum);
	List<BillDetails> getAllByClientId(long id);
	List<BillDetails> getAll();
	
	
	
}	
