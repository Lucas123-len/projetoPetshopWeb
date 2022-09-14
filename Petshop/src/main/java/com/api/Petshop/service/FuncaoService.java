package com.api.Petshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.Petshop.funcao.Funcao;
import com.api.Petshop.funcionario.Funcionario;
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
	
	public Funcao findById(Long codigo) {
		Optional<Funcao> result = repo.findById(codigo);
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
			throw new RuntimeException("Falha ao salvar o Função.");
		}
	}
	
	public Funcao update(Funcao f) {
		
		Funcao obj = findById(f.getCodigo());
		List<Funcionario> funcionariosAtuais = obj.getFuncionarios();
		funcionariosAtuais.removeAll(obj.getFuncionarios());
		verificaExclusaoFuncaoComFuncionarios(funcionariosAtuais);
		
		try {
			f.setDescricao(obj.getDescricao());
			return repo.save(f);
		}catch(Exception e){
			throw new RuntimeException("Falha ao atualizar função.");
		}
	}
	
	public void delete(Long codigo) {
		Funcao obj = findById(codigo);
		verificaExclusaoFuncaoComFuncionarios(obj.getFuncionarios());
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Função.");
		}
	}
	
	public void verificaDescricaoFuncao(String descricao) {
		List<Funcao> result = repo.findByDescricao(descricao);
		if(!result.isEmpty()) {
			throw new RuntimeException("Descrição de Função já cadastrado.");
		}
	}
	
	public void verificaExclusaoFuncaoComFuncionarios(List<Funcionario> funcionarios) {
		for(Funcionario f : funcionarios) {
			if(!f.getServicos().isEmpty()) {
				throw new RuntimeException("Função possui funcionários cadastrados que realizam serviços. Não pode ser excluído.");
			}
		}
		
	}
}
