package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.Bill;
import com.api.model.BillDetails;
import com.api.repositories.IBillRepository;

@Service
public class BillService implements IBillService {
	
	@Autowired
	private IBillRepository bR;
	
	@Override
	public List<Bill> getAllByBillNumber(long billNum) {
		return bR.findAllByBillNumber(billNum);
	}
	
	@Override
	public List<Bill> getAll() {
		return bR.findAll();
	}

	@Override
	public Optional<Bill> get(long billNum) {
		return bR.findById(billNum);
	}

	@Override
	public boolean save(Bill bill) {
		if(bR.existsById(bill.getBillNumber()))
			return false;
		else {
			bR.save(bill);
			return true;
		}
	}

	@Override
	public boolean delete(long id) {
		if(bR.existsById(id)) {	
			bR.deleteById(id);
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean update(Bill bill) {
		if(bR.existsById(bill.getBillNumber())) {	
			bR.save(bill);
			return true;
		}
		else
			return false;
	}
}
