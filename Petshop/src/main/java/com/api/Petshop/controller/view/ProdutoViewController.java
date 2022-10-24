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

import com.api.Petshop.produto.Produto;
import com.api.Petshop.service.ProdutoService;

@Controller
@RequestMapping(path = "/produtos")
public class ProdutoViewController {
	@Autowired
	private ProdutoService service;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("produtos",service.findAll());
		return "produtos";
	}
	
	@GetMapping(path = "/produto")
	public String cadastro(Model model) {
		model.addAttribute("produto", new Produto());
		return "FormProduto";
	}
	
	@PostMapping(path = "/produto")
	public String salvar(@Valid @ModelAttribute Produto produto, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormProduto";
		}
		//produto.setCodigo(0L);
		try {
			service.save(produto);
			model.addAttribute("msgSucesso","Produto cadastrado com sucesso.");
			model.addAttribute("produto", new Produto());
			return "FormProduto";
		}catch(Exception e) {
			model.addAttribute("msgErros", new ObjectError("produto",e.getMessage()));
			return "FormProduto";
		}
	}
	
	@GetMapping(path = "/produto/{codigo}")
	public String editar(@PathVariable("codigo") long codigo, Model model) {
		model.addAttribute("produto", service.findById(codigo));
		return "FormProduto";
	}
	
	@PutMapping(path = "/produto/{codigo}")
	public String atualizar(@Valid @ModelAttribute Produto produto, BindingResult result, @PathVariable("codigo") long codigo, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getAllErrors());
			return "FormProduto";
		}
		produto.setCodigo(codigo);
		try {
			service.update(produto);
			model.addAttribute("msgSucesso","Produto cadastrado com sucesso.");
			model.addAttribute("produto", produto);
			return "FormProduto";
		}catch(Exception e) {
			model.addAttribute("msgErros", new ObjectError("produto",e.getMessage()));
			return "FormProduto";
		}
	}
	
	@DeleteMapping(path = "/{codigo}/deletar")
	public String deletar(@PathVariable("codigo") long codigo) {
		service.delete(codigo);
		return "redirect:/produtos";
	}
}
