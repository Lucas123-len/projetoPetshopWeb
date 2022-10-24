package com.api.Petshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.Petshop.exception.NotFoundException;
import com.api.Petshop.funcionario.Funcionario;
import com.api.Petshop.repository.ServicoRepository;
import com.api.Petshop.servico.Servico;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository repo;
	
	public List<Servico> findAll(int page, int size){
		Pageable p = PageRequest.of(page, size);
		return repo.findAll(p).toList();
	}
	
	public List<Servico> findAll(){
		return repo.findAll();
	}
	
	public Servico findById(long codigo) {
		Optional<Servico> result = repo.findById(codigo);
		if(result.isEmpty()) {
			throw new NotFoundException("Serviço não encontrado.");
		}
		return result.get();
	}
	
	public Servico save(Servico s) {
		verificaDescricaoTipoCadastrado(s.getDescricao(),s.getTipo());
		try {
			return repo.save(s);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar o servico.");
		}
	}
	
	public Servico update(Servico s) {
		
		Servico obj = findById(s.getCodigo());
		List<Funcionario> funcionariosAtuais = obj.getFuncionarios();
		funcionariosAtuais.removeAll(s.getFuncionarios());
		
		try {
			s.setDescricao(obj.getDescricao());
			return repo.save(s);
		}catch(Exception e){
			throw new RuntimeException("Falha ao atualizar Serviço.");
		}
	}
	
	public void delete(long codigo) {
		Servico obj = findById(codigo);
		verificaExclusaoServicoSendoExecutado(obj.getFuncionarios());
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Serviço.");
		}
	}
	
	public void verificaDescricaoTipoCadastrado(String descricao, String tipo) {
		List<Servico> result = repo.findByDescricaoOrTipo(descricao, tipo);
		if(!result.isEmpty()) {
			throw new RuntimeException("Descrição ou Tipo já cadastrado.");
		}
	}
	
	public void verificaExclusaoServicoSendoExecutado(List<Funcionario> funcionarios) {
		for(Funcionario f : funcionarios) {
			if(!f.getClientes().isEmpty()) {
				throw new RuntimeException("Funcionários cadastrados realizam Serviço. Não pode ser excluído.");
			}
		}
	}
}
