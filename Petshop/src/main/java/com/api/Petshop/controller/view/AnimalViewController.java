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

import com.api.Petshop.animal.Animal;
import com.api.Petshop.service.AnimalService;

@Controller
@RequestMapping(path = "/animais")
public class AnimalViewController {
	@Autowired
	private AnimalService service;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("animais",service.findAll());
		return "animais";
	}
	
	@GetMapping(path = "/animal")
	public String cadastro(Model model) {
		model.addAttribute("animal", new Animal());
		return "FormAnimal";
	}
	
	@PostMapping(path = "/animal")
	public String salvar(@Valid @ModelAttribute Animal animal, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormAnimal";
		}
		animal.setCodigo((Long) null);
		try {
			service.save(animal);
			model.addAttribute("msgSucesso","Animal cadastrado com sucesso.");
			model.addAttribute("animal", new Animal());
			return "FormAnimal";
		}catch(Exception e) {
			model.addAttribute("msgErros", new ObjectError("animal",e.getMessage()));
			return "FormAnimal";
		}
	}
	
	@GetMapping(path = "/animal/{codigo}")
	public String editar(@PathVariable("codigo") Long codigo, Model model) {
		model.addAttribute("animal", service.findById(codigo));
		return "FormAnimal";
	}
	
	@PostMapping(path = "/animal/{codigo}")
	public String atualizar(@Valid @ModelAttribute Animal animal, BindingResult result, @PathVariable("codigo") Long codigo, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormAnimal";
		}
		animal.setCodigo((Long) null);
		try {
			service.update(animal);
			model.addAttribute("msgSucesso","Animal cadastrado com sucesso.");
			model.addAttribute("animal", animal);
			return "FormAnimal";
		}catch(Exception e) {
			model.addAttribute("msgErros", new ObjectError("animal",e.getMessage()));
			return "FormAnimal";
		}
	}
	
	@GetMapping(path = "deletar")
	public String deletar(@PathVariable("codigo") Long codigo) {
		service.delete(codigo);
		return "redirect:/animal";
	}
}
