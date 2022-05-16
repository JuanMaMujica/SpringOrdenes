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

import com.api.model.Client;
import com.api.service.IClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {

	@Autowired
	private IClientService cS;
	
	@GetMapping
	public ResponseEntity<List<Client>> getAll(){
		return ResponseEntity.ok().body(cS.getAll());
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Client c){
		if(cS.save(c))
			return ResponseEntity.ok().body("Client created");
		else 
			return ResponseEntity.badRequest().body("Bad Request pa");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id){
		if(cS.delete(id)) 
			return ResponseEntity.ok().body("Client deleted");
		else 
			return ResponseEntity.badRequest().body("Bad Request");
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Client c){
		if(cS.update(c)) {
			return ResponseEntity.ok().body("Client updated");
		} else 
			return ResponseEntity.badRequest().body("Bad Request");
	}
	
}
