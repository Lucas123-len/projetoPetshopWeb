package com.api.Petshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.Petshop.funcao.Funcao;
import com.api.Petshop.repository.FuncaoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FuncaoService {
	
	@Autowired
	private FuncaoRepository repo;
	
	public List<Funcao> findAll(int page, int size){
		Pageable p = PageRequest.of(page, size);
		return repo.findAll(p).toList();
	}
	
	public List<Funcao> findAll(){
		return repo.findAll();
	}
	
	public Funcao findByDescricao(String descricao) {
		Optional<Funcao> result = repo.findByDescricao(descricao);
		if(result.isEmpty()) {
			throw new RuntimeException("Função não encontrada.");
		}
		return result.get();
	}
	
	public Funcao save(Funcao f) {
		verificaDescricaoFuncao(f.getDescricao());
		try {
			return repo.save(f);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar o função.");
		}
	}
	
	public Funcao update(Funcao f) {
		Funcao obj = findByDescricao(f.getDescricao());
		try {
			f.setDescricao(obj.getDescricao());
			return repo.save(f);
		}catch(Exception e){
			throw new RuntimeException("Falha ao atualizar função.");
		}
	}
	
	public void delete(String descricao) {
		Funcao obj = findByDescricao(descricao);
		verificaExclusaoFuncao(obj);
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Função.");
		}
	}
	
	public void verificaDescricaoFuncao(String descricao) {
		List<Funcao> result = repo.findByDescricao(descricao);
		if(!result.isEmpty()) {
			throw new RuntimeException("Descrição de função já cadastrado.");
		}
	}
	
	public void verificaExclusaoFuncao(Funcao f) {
		if(!f.getFuncionarios().isEmpty()) {
			throw new RuntimeException("Função possui funcionários cadastrados. Não pode ser excluído.");
		}
	}
}
