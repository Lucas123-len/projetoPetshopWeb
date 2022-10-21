package com.api.Petshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.api.Petshop.exception.NotFoundException;
import com.api.Petshop.loja.Loja;
import com.api.Petshop.petshop.Petshop;
import com.api.Petshop.repository.PetshopRepository;

@Service
public class PetshopService {
	@Autowired
	private PetshopRepository repo;
	
	public List<Petshop> findAll(int page, int size){
		Pageable p = PageRequest.of(page, size);
		return repo.findAll(p).toList();
	}
	
	public List<Petshop> findAll(){
		return repo.findAll();
	}
	
	public Petshop findById(Long codigo) {
		Optional<Petshop> result = repo.findById(codigo);
		if(result.isEmpty()) {
			throw new NotFoundException("Petshop não encontrado.");
		}
		return result.get();
	}
	
	public Petshop save(Petshop p) {
		verificaCnpjCadastrado(p.getCnpj());
		try {
			return repo.save(p);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar o petshop.");
		}
	}
	
	public Petshop update(Petshop p) {
		Petshop obj = findById(p.getCodigo());
		List<Loja> lojasAtuais = obj.getLojas();
		lojasAtuais.removeAll(p.getLojas());
		verificaExclusaoLojaComFucionarios(lojasAtuais);
		
		try {
			p.setCnpj(obj.getCnpj());
			return repo.save(p);
		}catch(Exception e){
			throw new RuntimeException("Falha ao atualizar petshop.");
		}
	}
	
	public void delete(Long codigo) {
		Petshop obj = findById(codigo);
		verificaExclusaoLojaComFucionarios(obj.getLojas());
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Petshop.");
		}
	}
	
	public void verificaExclusaoLojaComFucionarios(List<Loja> lojas) {
		for(Loja l : lojas) {
			if(!l.getFuncionarios().isEmpty()) {
				throw new RuntimeException("Não é possível excluir lojas com funcionarios.");
			}
		}
	}
	
	public void verificaCnpjCadastrado(String cnpj) {
		List<Petshop> result = repo.findByCnpj(cnpj);
		if(!result.isEmpty()) {
			throw new RuntimeException("CNPJ já cadastrado.");
		}
	}
}
