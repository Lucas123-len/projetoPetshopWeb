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

import com.api.Petshop.loja.Loja;
import com.api.Petshop.service.LojaService;

@Controller
@RequestMapping(path = "/lojas")
public class LojaViewController {
	@Autowired
	private LojaService service;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("lojas", service.findAll());
		return "lojas";
	}
	
	@GetMapping(path = "/loja")
	public String cadastro(Model model) {
		model.addAttribute("loja",new Loja());
		return "FormLoja";
	}
	
	@PostMapping(path = "/loja")
	public String save(@Valid @ModelAttribute Loja loja, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormLoja";
		}
		//loja.setCodigo(0L);
		try {
			service.save(loja);
			model.addAttribute("msgSucesso","Loja cadastrada com sucesso");
			model.addAttribute("loja",new Loja());
			return "FormLoja";
		}catch(Exception e) {
			model.addAttribute("msgErros", new ObjectError("loja", e.getMessage()));
			return "FormLoja";
		}
	}
	
	@GetMapping(path = "/loja/{codigo}")
	public String autualizacao(@PathVariable("codigo") long codigo, Model model) {
		model.addAttribute("loja", service.findById(codigo));
		return "FormLoja";
	}
	
	@PutMapping(path = "/loja{codigo}")
	public String update(@Valid @ModelAttribute Loja loja, BindingResult result, @PathVariable("codigo") long codigo, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormLoja";
		}
		loja.setCodigo(codigo);
		try {
			service.update(loja);
			model.addAttribute("msgSucesso","Loja cadastrada com sucesso");
			model.addAttribute("loja",loja);
			return "FormLoja";
		}catch(Exception e) {
			model.addAttribute("msgErros", new ObjectError("loja", e.getMessage()));
			return "FormLoja";
		}
	}
	
	@DeleteMapping(path = "/{codigo}/deletar")
	public String deletar(@PathVariable("codigo") long codigo) {
		service.delete(codigo);
		return "redirect:/lojas";
	}
}
