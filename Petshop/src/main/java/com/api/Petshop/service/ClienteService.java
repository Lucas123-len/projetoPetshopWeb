package com.api.Petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.Petshop.cliente.Cliente;
import com.api.Petshop.pessoa.Pessoa;
import com.api.Petshop.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	
	public List<Cliente> findAll(int page, int size){
		Pageable p = PageRequest.of(page, size);
		return repo.findAll(p).toList();
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Cliente findByCpfOrNome(String cpf, String nome) {
		Optional<Cliente> result = repo.findByCpfOrNome(cpf,nome);
		if(result.isEmpty()) {
			throw new RuntimeException("Cliente não encontrado.");
		}
		return result.get();
	}
	
	public Cliente save(Cliente c) {
		verificaCpfNomeCadastrado(c.getCpf(), c.getNome());
		try {
			return repo.save(c);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar o cliente.");
		}
	}
	
	public Cliente update(Cliente c) {
		Cliente obj = findByCpfOrNome(c.getCpf(),c.getNome());
		try {
			c.setCpf(obj.getCpf());
			c.setNome(obj.getNome());
			return repo.save(c);
		}catch(Exception e){
			throw new RuntimeException("Falha ao atualizar cliente.");
		}
	}
	
	public void delete(String cpf,String nome) {
		Cliente obj = findByCpfOrNome(cpf,nome);
		verificaExclusaoCliente(obj);
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Cliente.");
		}
	}
	
	public void verificaCpfNomeCadastrado(String cpf, String nome) {
		List<Pessoa> result = repo.findByCpfOrNome(cpf, nome);
		if(!result.isEmpty()) {
			throw new RuntimeException("Cpf ou Nome já cadastrado.");
		}
	}
	
	public void verificaExclusaoCliente(Cliente c) {
		if(!c.getServicos().isEmpty()) {
			throw new RuntimeException("Cliente requisitou serviços. Não pode ser excluído.");
		}
	}
	
}
