package com.api.Petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.Petshop.exception.NotFoundException;
import com.api.Petshop.produto.Produto;
import com.api.Petshop.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	public List<Produto> findAll(int page, int size){
		Pageable p = PageRequest.of(page, size);
		return repo.findAll(p).toList();
	}
	
	public List<Produto> findAll(){
		return repo.findAll();
	}
	
	public Produto findById(long codigo) {
		Optional<Produto> result = repo.findById(codigo);
		if(result.isEmpty()) {
			throw new NotFoundException("Produto não encontrado.");
		}
		return result.get();
	}
	
	public Produto save(Produto p) {
		try {
			return repo.save(p);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar o Produto.");
		}
	}
	
	public Produto update(Produto p) {
		Produto obj = findById(p.getCodigo());
		try {
			p.setMarca(obj.getMarca());
			return repo.save(p);
		}catch(Exception e){
			throw new RuntimeException("Falha ao atualizar Produto.");
		}
	}
	
	public void delete(long codigo) {
		Produto obj = findById(codigo);
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Produto.");
		}
	}
}
