package com.api.Petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.Petshop.cliente.Cliente;
import com.api.Petshop.funcionario.Funcionario;
import com.api.Petshop.pessoa.Pessoa;
import com.api.Petshop.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository repo;
	
	public List<Funcionario> findAll(int page, int size){
		Pageable p = PageRequest.of(page, size);
		return repo.findAll(p).toList();
	}
	
	public List<Funcionario> findAll(){
		return repo.findAll();
	}
	
	public Funcionario findByCpfOrNome(String cpf, String nome) {
		Optional<Funcionario> result = repo.findByCpfOrNome(cpf,nome);
		if(result.isEmpty()) {
			throw new RuntimeException("Cliente não encontrado.");
		}
		return result.get();
	}
	
	public Funcionario update(Funcionario f) {
		Funcionario obj = findByCpfOrNome(f.getCpf(),f.getNome());
		try {
			f.setCpf(obj.getCpf());
			f.setNome(obj.getNome());
			return repo.save(f);
		}catch(Exception e){
			throw new RuntimeException("Falha ao atualizar funcionario.");
		}
	}
	
	public Funcionario save(Funcionario f) {
		verificaCpfNomeCadastrado(f.getCpf(), f.getNome());
		try {
			return repo.save(f);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar o funcionario.");
		}
	}
	
	public void delete(String cpf, String nome) {
		Funcionario obj = findByCpfOrNome(cpf,nome);
		verificaExclusaoFuncionario(obj);
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Funcionario.");
		}
	}
	
	public void verificaCpfNomeCadastrado(String cpf, String nome) {
		List<Pessoa> result = repo.findByCpfOrNome(cpf, nome);
		if(!result.isEmpty()) {
			throw new RuntimeException("Cpf ou Nome já cadastrado.");
		}
	}
	
	public void verificaExclusaoFuncionario(Funcionario f) {
		if(!f.getServicos().isEmpty()) {
			throw new RuntimeException("Funcionario realiza serviços. Não pode ser excluído.");
		}
	}
}
