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

import com.api.Petshop.funcao.Funcao;
import com.api.Petshop.service.FuncaoService;

@ResponseBody
@Controller
@RequestMapping(path = "/funcoes")
public class FuncaoViewController {
	@Autowired
	private FuncaoService service;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("funcoes",service.findAll());
		return "funcoes";
	}
	
	@GetMapping(path = "/funcao")
	public String cadastro(Model model) {
		model.addAttribute("funcao", new Funcao());
		return "FormFuncao";
	}
	
	@PostMapping(path = "/funcao")
	public String salvar(@Valid @ModelAttribute Funcao funcao, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormFuncao";
		}
		//funcao.setCodigo(0L);
		try {
			service.save(funcao);
			model.addAttribute("msgSucesso","Funcao cadastrada com sucesso.");
			model.addAttribute("funcao", new Funcao());
			return "FormFuncao";
		}catch(Exception e) {
			model.addAttribute("msgErros", new ObjectError("funcao",e.getMessage()));
			return "FormFuncao";
		}
	}
	
	@GetMapping(path = "/funcao/{codigo}")
	public String editar(@PathVariable("codigo") long codigo, Model model) {
		model.addAttribute("funcao", service.findById(codigo));
		return "FormFuncao";
	}
	
	@PutMapping(path = "/funcao/{codigo}")
	public String atualizar(@Valid @ModelAttribute Funcao funcao, BindingResult result, @PathVariable("codigo") long codigo, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormFuncao";
		}
		funcao.setCodigo(codigo);
		try {
			service.update(funcao);
			model.addAttribute("msgSucesso","Funcao cadastrado com sucesso.");
			model.addAttribute("funcao", funcao);
			return "FormFuncao";
		}catch(Exception e) {
			model.addAttribute("msgErros", new ObjectError("funcao",e.getMessage()));
			return "FormFuncao";
		}
	}
	
	@DeleteMapping(path = "/{codigo}/deletar")
	public String deletar(@PathVariable("codigo") long codigo) {
		service.delete(codigo);
		return "redirect:/funcoes";
	}
}
