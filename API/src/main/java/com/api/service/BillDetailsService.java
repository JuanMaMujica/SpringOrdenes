package com.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.BillDetails;
import com.api.repositories.IBillDetailsRepository;

@Service
public class BillDetailsService implements IBillDetailsService{

	@Autowired
	private IBillDetailsRepository bdR;
	
	@Override
	public List<BillDetails> getAll() {
		return bdR.findAll();
	}
	
	@Override
	public List<BillDetails> getAllByBillNumber(long billNum) {
		return bdR.findAllByBillNumber(billNum);
	}

	@Override
	public List<BillDetails> getAllByClientId(long id) {
		return bdR.findAllByClientId(id);
	}
	
	@Override
	public Optional<BillDetails> get(long id) {
		return bdR.findById(id);
	}

	@Override
	public boolean save(BillDetails bd) {
		if(bdR.existsById(bd.getId()))
			return false;
		else {
			bdR.save(bd);
			return true;
		}
	}

	@Override
	public boolean delete(long id) {
		if(bdR.existsById(id)) {	
			bdR.deleteById(id);
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean update(BillDetails bd) {
		if(bdR.existsById(bd.getId())) {	
			bdR.save(bd);
			return true;
		}
		else
			return false;
	}

}
