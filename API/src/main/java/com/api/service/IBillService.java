package com.api.service;

import java.util.List;
import java.util.Optional;

import com.api.model.Bill;

public interface IBillService {
	
	List<Bill> getAll();
	Optional<Bill> get(long billNum);
	boolean save(Bill bill);
	boolean delete(long billNum);
	boolean update(Bill bill);
	List<Bill> getAllByBillNumber(long billNum);
	
}
