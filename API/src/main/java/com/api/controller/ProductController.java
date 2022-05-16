package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.model.Product;
import com.api.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private IProductService pS;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAll(){
		return ResponseEntity.ok().body(pS.getAll());
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Product p){
		if(pS.save(p))
			return ResponseEntity.ok().body("Product created");
		else 
			return ResponseEntity.badRequest().body("Bad Request pa");
	}
	
	@DeleteMapping("/{code}")
	public ResponseEntity<?> delete(@PathVariable("code") long code){
		if(pS.delete(code)) 
			return ResponseEntity.ok().body("Product deleted");
		else 
			return ResponseEntity.badRequest().body("Bad Request");
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Product p){
		if(pS.update(p)) {
			return ResponseEntity.ok().body("Product updated");
		} else 
			return ResponseEntity.badRequest().body("Bad Request");
	}
	
	
	
}
