package com.api.Petshop.controller.view;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.api.Petshop.funcionario.Funcionario;
import com.api.Petshop.repository.PermissaoRepository;
import com.api.Petshop.service.FuncionarioService;

@Controller
@RequestMapping(path = "/funcionarios")
public class FuncionarioViewController {
	@Autowired
	private FuncionarioService service;
	
	@Autowired
	private PermissaoRepository permissaoRepo;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("funcionarios", service.findAll());
		return "funcionarios";
	}
	
	@GetMapping(path = "/funcionario")
	public String cadastro(Model model) {
		model.addAttribute("funcionario",new Funcionario());
		model.addAttribute("permissoes",permissaoRepo.findAll());
		return "FormFuncionario";
	}
	
	@PostMapping(path = "/funcionario")
	public String save(@Valid @ModelAttribute Funcionario funcionario, BindingResult result, @RequestParam("confirmarSenha") String confirmarSenha, Model model) {
		
		//valores a serem retornados
		model.addAttribute("permissoes", permissaoRepo.findAll());
		
		if(result.hasErrors()) {
			model.addAttribute("msgErros",result.getAllErrors());
			return "FormFuncionario";
		}
		if(!funcionario.getSenha().equals(confirmarSenha)) {
			model.addAttribute("msgErros", new ObjectError("funcionario","Campos Senha e Confirmar Senha devem ser iguais."));
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
		model.addAttribute("permissoes", permissaoRepo.findAll());
		return "FormFuncionario";
	}
	
	@PostMapping(path = "/funcionario/{codigo}")
	public String update(@Valid @ModelAttribute Funcionario funcionario, BindingResult result, @PathVariable("codigo") Long codigo, Model model) {
		
		//valores a serem retornados
		model.addAttribute("permissoes", permissaoRepo.findAll());		
		
		List<FieldError> list = new ArrayList<>();
		for(FieldError fe : result.getFieldErrors()) {
			if(!fe.getField().equals("senha")) {
				list.add(fe);
			}
		}
		if(!list.isEmpty()) {
			model.addAttribute("msgErros",list);
			return "FormFuncionario";
		}
		
		funcionario.setCodigo(codigo);
		try {
			service.update(funcionario,"","","");
			model.addAttribute("msgSucesso","Funcionario atualizado com sucesso");
			model.addAttribute("funcionario",funcionario);
			return "FormFuncionario";
		}catch(Exception e) {
			model.addAttribute("msgErros",new ObjectError("funcionario", e.getMessage()));
			return "FormFuncionario";
		}
	}
	
	@GetMapping(path = "/{codigo}/deletar")
	public String deletar(@PathVariable("codigo") Long codigo) {
		service.delete(codigo);
		return "redirect:/funcionarios";
	}
	
	//Meus Dados
	@GetMapping(path = "/meusdados")
	public String getMeusDados(@AuthenticationPrincipal User user, Model model) {
		//email
		Funcionario funcionario = service.findByEmail(user.getUsername());
		model.addAttribute("funcionario",funcionario);
		return "FormMeusDados";
	}
	
	@PostMapping(path = "/meusdados")
	public String updateMeusDados(@Valid @ModelAttribute Funcionario funcionario, BindingResult result, @AuthenticationPrincipal User user, @RequestParam("senhaAtual") String senhaAtual, @RequestParam("novaSenha") String novaSenha, @RequestParam("confirmarNovaSenha") String confirmarNovaSenha, Model model) {
		List<FieldError> list = new ArrayList<>();
		for(FieldError fe : result.getFieldErrors()) {
			if(!fe.getField().equals("senha") && !fe.getField().equals("permissoes")) {
				list.add(fe);
			}
		}
		if(!list.isEmpty()) {
			model.addAttribute("msgErros",list);
			return "FormMeusdados";
		}
		
		Funcionario funcionarioBD = service.findByEmail(user.getUsername());
		
		if(funcionarioBD.getCodigo() != funcionario.getCodigo()) {
			throw new RuntimeException("Acesso Negado.");
		}
		try {
			funcionario.setPermissoes(funcionarioBD.getPermissoes());
			service.update(funcionario,senhaAtual,novaSenha,confirmarNovaSenha);
			model.addAttribute("msgSucesso","Funcionario atualizado com sucesso");
			model.addAttribute("funcionario",funcionario);
			return "FormMeusDados";
		}catch(Exception e) {
			model.addAttribute("msgErros",new ObjectError("funcionario", e.getMessage()));
			return "FormMeusDados";
		}
	}
}
