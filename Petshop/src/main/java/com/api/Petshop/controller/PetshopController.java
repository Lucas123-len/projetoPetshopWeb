package com.api.Petshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.Petshop.petshop.Petshop;
import com.api.Petshop.service.PetshopService;

@RestController
@RequestMapping(path = "/apirest/petshops")

public class PetshopController {
	
	@Autowired
	private PetshopService service;
	
	@GetMapping
	public ResponseEntity getAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@GetMapping(path = "/(cnpj)")
	public ResponseEntity getOne(@PathVariable("cnpj") String cnpj) {
		return ResponseEntity.ok(service.findByCnpj(cnpj));
	}
	
	@PostMapping
	public ResponseEntity save(@Valid @RequestBody Petshop petshop) {
		petshop.setCnpj(null);
		service.save(petshop);
		return ResponseEntity.status(HttpStatus.CREATED).body(petshop);
	}
	
	@PutMapping("/(cnpj)")
	public ResponseEntity update(@PathVariable("cnpj") String cnpj, @Valid @RequestBody Petshop petshop) {
		petshop.setCnpj(cnpj);
		service.update(petshop);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(path = "/(cnpj)")
	public ResponseEntity delete(@PathVariable("cnpj") String cnpj) {
		service.delete(cnpj);
		return ResponseEntity.ok().build();
	}
}