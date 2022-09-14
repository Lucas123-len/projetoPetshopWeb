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
import com.api.Petshop.servico.Servico;

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
	
	public Cliente findById(Long codigo) {
		Optional<Cliente> result = repo.findById(codigo);
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
		
		Cliente obj = findById(c.getCodigo());
		List<Servico> servicosRealizados = obj.getServicos();
		servicosRealizados.removeAll(c.getServicos());
		verificaExclusaoCliente(servicosRealizados);
		try {
			c.setCpf(obj.getCpf());
			c.setNome(obj.getNome());
			return repo.save(c);
		}catch(Exception e){
			throw new RuntimeException("Falha ao atualizar Cliente.");
		}
	}
	
	public void delete(Long codigo) {
		Cliente obj = findById(codigo);
		verificaExclusaoCliente(obj.getServicos());
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Cliente.");
		}
	}
	
	public void verificaCpfNomeCadastrado(String cpf, String nome) {
		List<Pessoa> result = repo.findByCpfOrNomeOrId(cpf, nome);
		if(!result.isEmpty()) {
			throw new RuntimeException("Cpf ou Nome já cadastrado.");
		}
	}
	
	public void verificaExclusaoCliente(List<Servico> servicos) {
		for(Servico s : servicos)
		if(!s.getFuncionarios().isEmpty()) {
			throw new RuntimeException("Cliente requisitou serviços. Não pode ser excluído.");
		}
	}
	
}
