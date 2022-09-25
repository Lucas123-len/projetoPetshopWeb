package com.api.Petshop.service;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.Petshop.cliente.Cliente;
import com.api.Petshop.exception.NotFoundException;
import com.api.Petshop.funcionario.Funcionario;
import com.api.Petshop.pessoa.Pessoa;
import com.api.Petshop.repository.FuncionarioRepository;
import com.api.Petshop.servico.Servico;

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
	
	public Funcionario findById(Long codigo) {
		Optional<Funcionario> result = repo.findById(codigo);
		if(result.isEmpty()) {
			throw new NotFoundException("Cliente não encontrado.");
		}
		return result.get();
	}
	
	public Funcionario save(Funcionario f) {
		verificaCpfNomeCadastrado(f.getCpf(), f.getNome());
		try {
			return repo.save(f);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar o Funcionario.");
		}
	}
	
	public Funcionario update(Funcionario f) {
		
		Funcionario obj = findById(f.getCodigo());
		List<Servico> servicosAtuais = obj.getServicos();
		servicosAtuais.removeAll(f.getServicos());
		verificaExclusaoFuncionario(servicosAtuais);
		
		try {
			f.setCpf(obj.getCpf());
			return repo.save(f);
		}catch(Exception e){
			Throwable t = e;
			while(t.getCause() != null) {
				t = t.getCause();
				if(t instanceof ConstraintViolationException) {
					throw((ConstraintViolationException) t);
				}
			}
			throw new RuntimeException("Falha ao atualizar Funcionario.");
		}
	}
	
	public void delete(Long codigo) {
		Funcionario obj = findById(codigo);
		verificaExclusaoFuncionario(obj.getServicos());
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
	
	public void verificaExclusaoFuncionario(List<Servico> servicos) {
		for(Servico s : servicos) {
			if(!s.getClientes().isEmpty()) {
				throw new RuntimeException("Funcionario realiza serviços que possuem clientes vinculados. Não pode ser excluído.");
			}
		}
	}
}
