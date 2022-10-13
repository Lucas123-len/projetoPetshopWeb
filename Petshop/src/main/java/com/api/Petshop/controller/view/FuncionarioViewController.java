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

import com.api.Petshop.funcionario.Funcionario;
import com.api.Petshop.service.FuncionarioService;

@Controller
@RequestMapping(path = "/funcionarios")
public class FuncionarioViewController {
	@Autowired
	private FuncionarioService service;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("funcionarios", service.findAll());
		return "funcionarios";
	}
	
	@GetMapping(path = "/funcionario")
	public String cadastro(Model model) {
		model.addAttribute("funcionario",new Funcionario());
		return "FormFuncionario";
	}
	
	@PostMapping(path = "/funcionario")
	public String save(@Valid @ModelAttribute Funcionario funcionario, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros",result.getAllErrors());
			return "FormFuncionario";
		}
		funcionario.setCodigo((Long) null);
		try {
			service.save(funcionario);
			model.addAttribute("msgSucesso","Funcionario cadastrado com sucesso");
			model.addAttribute("funcionario",new Funcionario());
			return "FormFuncionario";
		}catch(Exception e) {
			model.addAttribute("msgErros",new ObjectError("Funcionario", e.getMessage()));
			return "FormFuncionario";
		}
	}
	
	@GetMapping(path = "/funcionario/{codigo}")
	public String atualizacao(@PathVariable("codigo") Long codigo, Model model) {
		model.addAttribute("funcionario",service.findById(codigo));
		return "FormFuncionario";
	}
	
	@PostMapping(path = "/funcionario/{codigo}")
	public String update(@Valid @ModelAttribute Funcionario funcionario, BindingResult result, @PathVariable("codigo") Long codigo, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros",result.getAllErrors());
			return "FormFuncionario";
		}
		funcionario.setCodigo((Long) null);
		try {
			service.save(funcionario);
			model.addAttribute("msgSucesso","Funcionario cadastrado com sucesso");
			model.addAttribute("funcionario",new Funcionario());
			return "FormFuncionario";
		}catch(Exception e) {
			model.addAttribute("msgErros",new ObjectError("Funcionario", e.getMessage()));
			return "FormFuncionario";
		}
	}
	
	@GetMapping(path = "/{codigo}/deletar")
	public String deletar(@PathVariable("codigo") Long codigo) {
		service.delete(codigo);
		return "redirect:/funcionarios";
	}
}
