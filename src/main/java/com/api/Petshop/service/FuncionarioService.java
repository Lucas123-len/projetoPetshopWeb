package com.api.Petshop.service;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
	
	public Funcionario findByEmail(String email){
		return repo.findByEmail(email);
	}
	
	public Funcionario findById(Long codigo) {
		Optional<Funcionario> result = repo.findById(codigo);
		if(result.isEmpty()) {
			throw new NotFoundException("Cliente não encontrado.");
		}
		return result.get();
	}
	
	public Funcionario save(Funcionario f) {
		verificaCpfEmailCadastrado(f.getCpf(), f.getNome());
		removePermissoesNulas(f);
		try {
			f.setSenha(new BCryptPasswordEncoder().encode(f.getSenha()));
			return repo.save(f);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar o Funcionario.");
		}
	}
	
	public Funcionario update(Funcionario f, String senhaAtual, String novaSenha, String confirmaNovaSenha) {
		
		Funcionario obj = findById(f.getCodigo());
		List<Servico> servicosAtuais = obj.getServicos();
		servicosAtuais.removeAll(f.getServicos());
		verificaExclusaoFuncionario(servicosAtuais);
		removePermissoesNulas(f);
		alterarSenha(obj,senhaAtual,novaSenha,confirmaNovaSenha);
		try {
			f.setCpf(obj.getCpf());
			f.setEmail(obj.getEmail());
			f.setSenha(obj.getSenha());
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
	
	public void verificaCpfEmailCadastrado(String cpf, String email) {
		List<Pessoa> result = repo.findByCpfOrEmail(cpf, email);
		if(!result.isEmpty()) {
			throw new RuntimeException("Cpf ou Nome já cadastrado.");
		}
	}
	
	private void alterarSenha(Funcionario obj, String senhaAtual, String novaSenha, String confirmaNovaSenha) {
		BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
		if(!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmaNovaSenha.isBlank()) {
			if(!crypt.matches(senhaAtual, obj.getSenha())) {
				throw new RuntimeException("Senha atual esta incorreta.");
			}
			if(!novaSenha.equals(confirmaNovaSenha)) {
				throw new RuntimeException("Nova Senha e Confirmar Nova Senha não conferem.");
			}
			obj.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
		}
	}
	
	public void verificaExclusaoFuncionario(List<Servico> servicos) {
		for(Servico s : servicos) {
			if(!s.getClientes().isEmpty()) {
				throw new RuntimeException("Funcionario realiza serviços que possuem clientes vinculados. Não pode ser excluído.");
			}
		}
	}
	
	public void removePermissoesNulas(Funcionario f) {
		f.getPermissoes().removeIf( p -> p.getCodigo() == 0L);
		
		if(f.getPermissoes().isEmpty()) {
			throw new RuntimeException("Funcionario deve conter no minimo 1 permissao.");
		}
	}
}
