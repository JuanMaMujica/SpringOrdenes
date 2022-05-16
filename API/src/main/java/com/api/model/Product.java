package com.api.model;



import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	private long code;
	private String name;
	private int stock;
	
	public Product(long code) {
		this.code = code;
	}
	
	
	
	
}
