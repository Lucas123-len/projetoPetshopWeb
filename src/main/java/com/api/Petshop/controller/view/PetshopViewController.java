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

import com.api.Petshop.petshop.Petshop;
import com.api.Petshop.service.PetshopService;

@Controller
@RequestMapping(path = "/petshops")
public class PetshopViewController {
	@Autowired
	private PetshopService service;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("petshops",service.findAll());
		return "petshops";
	}
	
	@GetMapping(path = "/petshop")
	public String cadastro(Model model) {
		model.addAttribute("petshop",new Petshop());
		return "FormPetshop";
	}
	
	@PostMapping(path = "/petshop")
	public String save(@Valid @ModelAttribute Petshop petshop, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormPetshop";
		}
		petshop.setCodigo((Long) null);
		try {
			service.save(petshop);
			model.addAttribute("msgSucesso","Petshop cadastrado com sucesso");
			model.addAttribute("petshop", new Petshop());
			return "FormPetshop";
		}catch(Exception e){
			model.addAttribute("msgErros",new ObjectError("Petshop", e.getMessage()));
			return "FormPetshop";
		}
	}
	
	@GetMapping(path = "/petshop/{codigo}")
	public String alterar(@PathVariable("codigo") Long codigo, Model model) {
		model.addAttribute("petshop",service.findById(codigo));
		return "FormPetshop";
	}
	
	@PostMapping(path = "/petshop/{codigo}")
	public String update(@Valid @ModelAttribute Petshop petshop, BindingResult result, @PathVariable("codigo") Long codigo, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormPetshop";
		}
		petshop.setCodigo(codigo);
		try {
			service.update(petshop);
			model.addAttribute("msgSucesso","Petshop atualizado com sucesso!");
			model.addAttribute("petshop",petshop);
			return "FormPetshop";
		}catch(Exception e){
			model.addAttribute("msgErros",new ObjectError("Petshop", e.getMessage()));
			return "FormPetshop";
		}
	}
	
	@GetMapping(path = "/{codigo}/deletar")
	public String deletar(@PathVariable("codigo") Long codigo) {
		service.delete(codigo);
		return "redirect:/petshops";
	}
}
