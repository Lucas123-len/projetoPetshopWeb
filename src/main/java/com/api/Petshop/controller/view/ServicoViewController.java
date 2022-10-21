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

import com.api.Petshop.service.ServicoService;
import com.api.Petshop.servico.Servico;

@Controller
@RequestMapping(path = "/servicos")
public class ServicoViewController {
	@Autowired
	private ServicoService service;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("servico",service.findAll());
		return "servicos";
	}
	
	@GetMapping(path = "/servico")
	public String cadastro(Model model) {
		model.addAttribute("servico", new Servico());
		return "FormServico";
	}
	
	@PostMapping(path = "/servico")
	public String salvar(@Valid @ModelAttribute Servico servico, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormServico";
		}
		servico.setCodigo((Long) null);
		try {
			service.save(servico);
			model.addAttribute("msgSucesso","Servico cadastrado com sucesso.");
			model.addAttribute("servico", new Servico());
			return "FormServico";
		}catch(Exception e) {
			model.addAttribute("msgErros", new ObjectError("servico",e.getMessage()));
			return "FormServico";
		}
	}
	
	@GetMapping(path = "/servico/{codigo}")
	public String editar(@PathVariable("codigo") Long codigo, Model model) {
		model.addAttribute("servico", service.findById(codigo));
		return "FormServico";
	}
	
	@PostMapping(path = "/servico/{codigo}")
	public String atualizar(@Valid @ModelAttribute Servico servico, BindingResult result, @PathVariable("codigo") Long codigo, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormServico";
		}
		servico.setCodigo((Long) null);
		try {
			service.update(servico);
			model.addAttribute("msgSucesso","Servico cadastrado com sucesso.");
			model.addAttribute("servico", servico);
			return "FormServico";
		}catch(Exception e) {
			model.addAttribute("msgErros", new ObjectError("servico",e.getMessage()));
			return "FormServico";
		}
	}
	
	@GetMapping(path = "deletar")
	public String deletar(@PathVariable("codigo") Long codigo) {
		service.delete(codigo);
		return "redirect:/servicos";
	}
}
