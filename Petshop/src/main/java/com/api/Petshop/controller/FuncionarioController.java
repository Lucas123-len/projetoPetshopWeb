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

import com.api.Petshop.funcionario.Funcionario;
import com.api.Petshop.service.FuncionarioService;

@RestController
@RequestMapping(path = "/apirest/funcionarios")

public class FuncionarioController {
	@Autowired
	private FuncionarioService service;
	
	@GetMapping
	public ResponseEntity getAll(@RequestParam(name = "page", defaultValue = "0", required = false) int page, @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
		return ResponseEntity.ok(service.findAll(page,size));
	}
	
	@GetMapping(path = "/(codigo)")
	public ResponseEntity getOne(@PathVariable("codigo") Long codigo) {
		return ResponseEntity.ok(service.findById(codigo));
	}
	
	@PostMapping
	public ResponseEntity save(@Valid @RequestBody Funcionario funcionario) {
		funcionario.setCodigo((Long) null);
		service.save(funcionario);
		return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
	}
	
	@PutMapping(path = "/(codigo)")
	public ResponseEntity update(@PathVariable("codigo") Long codigo, @RequestBody Funcionario funcionario) {
		funcionario.setCodigo(codigo);
		service.update(funcionario);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping(path = "/(codigo)")
	public ResponseEntity delete(@PathVariable("codigo") Long codigo) {
		service.delete(codigo);
		return ResponseEntity.ok().build();
	}
}
