package com.api.Petshop.controller.apirest;

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
	
	@ResponseBody
	@GetMapping
	public ResponseEntity getAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}
	
	@ResponseBody
	@GetMapping(path = "/(codigo)")
	public ResponseEntity getOne(@PathVariable("codigo") long codigo) {
		return ResponseEntity.ok(service.findById(codigo));
	}
	
	@ResponseBody
	@PostMapping
	public ResponseEntity save(@Valid @RequestBody Petshop petshop) {
		petshop.setCodigo(0L);
		service.save(petshop);
		return ResponseEntity.status(HttpStatus.CREATED).body(petshop);
	}
	
	@ResponseBody
	@PutMapping("/(codigo)")
	public ResponseEntity update(@PathVariable("codigo") long codigo, @Valid @RequestBody Petshop petshop) {
		petshop.setCodigo(codigo);
		service.update(petshop);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@ResponseBody
	@DeleteMapping(path = "/(codigo)")
	public ResponseEntity delete(@PathVariable("/(codigo)") long codigo) {
		service.delete(codigo);
		return ResponseEntity.ok().build();
	}
}
