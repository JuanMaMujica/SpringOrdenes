package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.Product;
import com.api.repositories.IProductRepository;

@Service
public class ProductService implements IProductService{

	@Autowired
	private IProductRepository pR;
	
	@Override
	public List<Product> getAll() {
		return pR.findAll();
	}

	@Override
	public boolean save(Product p) {
		if(pR.existsById(p.getCode()))
			return false;
		else {
			pR.save(p);
			return true;
		}
	}

	@Override
	public boolean delete(long code) {
		if(pR.existsById(code)) {	
			pR.deleteById(code);
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean update(Product p) {
		if(pR.existsById(p.getCode())) {	
			pR.save(p);
			return true;
		}
		else
			return false;
	}

}
