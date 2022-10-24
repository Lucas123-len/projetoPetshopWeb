package com.api.Petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.Petshop.exception.NotFoundException;
import com.api.Petshop.funcionario.Funcionario;
import com.api.Petshop.loja.Loja;
import com.api.Petshop.repository.LojaRepository;

@Service
public class LojaService {
	
	@Autowired
	public LojaRepository repo;
	
	public List<Loja> findAll(int page, int size){
		Pageable p = PageRequest.of(page, size);
		return repo.findAll(p).toList();
	}
	
	public List<Loja> findAll(){
		return repo.findAll();
	}
	
	public Loja findById(long codigo) {
		Optional<Loja> result = repo.findById(codigo);
		if(result.isEmpty()) {
			throw new NotFoundException("Loja não encontrada.");
		}
		return result.get();
	}
	
	public Loja save(Loja l) {
		verificaNomeGerenteOuTelefone(l.getNomeGerente(),l.getTelefone());
		try {
			return repo.save(l);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar o loja.");
		}
	}
	
	public Loja update(Loja l) {
		
		Loja obj = findById(l.getCodigo());
		List<Funcionario> funcionariosAtuais = obj.getFuncionarios();
		funcionariosAtuais.removeAll(l.getFuncionarios());
		
		
		try {
			l.setNomeGerente(obj.getNomeGerente());
			return repo.save(l);
		}catch(Exception e){
			throw new RuntimeException("Falha ao atualizar loja.");
		}
	}
	
	public void delete(long codigo) {
		Loja obj = findById(codigo);
		verificaExclusaoLojaComFuncionarios(obj.getFuncionarios());
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar a Loja.");
		}
	}
	
	public void verificaNomeGerenteOuTelefone(String nomeGerente, String telefone) {
		List<Loja> result = repo.findByNomeGerenteOrTelefone(nomeGerente, telefone);
		if(!result.isEmpty()) {
			throw new RuntimeException("Nome ou telefone já cadastrado.");
		}
	}
	
	public void verificaExclusaoLojaComFuncionarios(List<Funcionario> funcionarios) {
		for(Funcionario f : funcionarios) {
			if(!f.getClientes().isEmpty()) {
				throw new RuntimeException("Loja possui Funcionários atendendo clientes. Não pode ser excluído.");
			}
		}
	}
}
