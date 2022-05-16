package com.api.service;

import java.util.List;

import com.api.model.Product;

public interface IProductService {
	List<Product> getAll();
	boolean save(Product p);
	boolean delete(long code);
	boolean update(Product bd);
}
