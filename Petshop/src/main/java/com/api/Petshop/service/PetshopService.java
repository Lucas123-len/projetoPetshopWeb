package com.api.Petshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
	
	public Petshop findByCnpjOrNome(String cnpj) {
		Optional<Petshop> result = repo.findByCnpj(cnpj);
		if(result.isEmpty()) {
			throw new RuntimeException("Petshop não encontrado.");
		}
		return result.get();
	}
	
	public Petshop save(Petshop p) {
		try {
			return repo.save(p);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar o petshop.");
		}
	}
	
	public Petshop update(Petshop p) {
		Petshop obj = findByCnpj(p.getCnpj());
		List<Loja> lojasAtuais = obj.getLojas();
		lojasAtuais.removeAll(p.getLojas());
		verificaExclusaoLojaComFuncionarios(lojasAtuais);
		
		try {
			p.setCnpj(obj.getCnpj());
			return repo.save(p);
		}catch(Exception e){
			throw new RuntimeException("Falha ao atualizar petshop.");
		}
	}
	
	public void delete(String cnpj) {
		Petshop obj = findByCnpj(cnpj);
		verificaExclusaoLojaComFuncionarios(obj.getLojas());
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Petshop.");
		}
	}
	
	public void verificaExlusaoLojaComFucionarios(List<Loja> lojas) {
		for(Loja l : lojas) {
			if(!l.getFuncionarios().isEmpty()) {
				throw new RuntimeException("Não é possível excluir lojas com funcionarios.");
			}
		}
	}
}
