package com.api.Petshop.controller.view;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.api.Petshop.cliente.Cliente;
import com.api.Petshop.service.ClienteService;

@Controller
@RequestMapping(path = "/clientes")
public class ClienteViewController {
	@Autowired
	private ClienteService service;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("clientes",service.findAll());
		return "clientes";
	}
	
	@GetMapping(path = "/cliente")
	public String cadastro(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "FormCliente";
	}
	
	@PostMapping(path = "/cliente")
	public String salvar(@Valid @ModelAttribute Cliente cliente, BindingResult result, @RequestParam("file") MultipartFile file, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormCliente";
		}
		cliente.setCodigo((Long) null);
		try {
			service.save(cliente, file);
			model.addAttribute("msgSucesso","Cliente cadastrado com sucesso.");
			model.addAttribute("cliente", new Cliente());
			return "FormCliente";
		}catch(Exception e) {
			model.addAttribute("msgErros", new ObjectError("cliente",e.getMessage()));
			return "FormCliente";
		}
	}
	
	@GetMapping(path = "/cliente/{codigo}")
	public String editar(@PathVariable("codigo") Long codigo, Model model) {
		model.addAttribute("cliente", service.findById(codigo));
		return "FormCliente";
	}
	
	@PostMapping(path = "/cliente/{codigo}")
	public String atualizar(@Valid @ModelAttribute Cliente cliente, BindingResult result, @RequestParam("file") MultipartFile file, @PathVariable("codigo") Long codigo, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormCliente";
		}
		cliente.setCodigo((Long) null);
		try {
			service.update(cliente, file);
			model.addAttribute("msgSucesso","Cliente cadastrado com sucesso.");
			model.addAttribute("cliente", cliente);
			return "FormCliente";
		}catch(Exception e) {
			model.addAttribute("msgErros", new ObjectError("cliente",e.getMessage()));
			return "FormCliente";
		}
	}
	
	@GetMapping(path = "deletar")
	public String deletar(@PathVariable("codigo") Long codigo) {
		service.delete(codigo);
		return "redirect:/clientes";
	}
}
