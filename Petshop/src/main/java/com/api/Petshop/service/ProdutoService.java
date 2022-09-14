package com.api.Petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	
	public Produto findByMarca(String marca, long codigo) {
		Optional<Produto> result = repo.findByMarca(marca,codigo);
		if(result.isEmpty()) {
			throw new RuntimeException("Produto não encontrado.");
		}
		return result.get();
	}
	
	public Produto save(Produto p) {
		try {
			return repo.save(p);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar o produto.");
		}
	}
	
	public Produto update(Produto p) {
		Produto obj = findByMarca(p.getMarca(),p.getCodigo());
		try {
			p.setMarca(obj.getMarca());
			return repo.save(p);
		}catch(Exception e){
			throw new RuntimeException("Falha ao atualizar produto.");
		}
	}
	
	public void delete(String marca, long codigo) {
		Produto obj = findByMarca(marca,codigo);
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Produto.");
		}
	}
}
