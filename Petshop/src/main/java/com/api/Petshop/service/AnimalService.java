package com.api.Petshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.api.Petshop.animal.Animal;
import com.api.Petshop.repository.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	private AnimalRepository repo;
	
	public List<Animal> findAll(int page, int size){
		Pageable p = PageRequest.of(page, size);
		return repo.findAll(p).toList();
	}
	
	public List<Animal> findAll(){
		return repo.findAll();
	}
	
	public Animal findByNomeOrRaca(String nome, String raca) {
		Optional<Animal> result = repo.findByNomeOrRaca(nome, raca);
		if(result.isEmpty()) {
			throw new RuntimeException("Animal não encontrado.");
		}
		return result.get();
	}
	
	public Animal save(Animal a) {
		verificaNomeOuRacaCadastrado(a.getNome(),a.getRaca());
		try {
			return repo.save(a);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar animal.");
		}
	}
	
	public Animal update(Animal a) {
		Animal obj = findByNomeOrRaca(a.getNome(),a.getRaca());
		try {
			a.setNome(obj.getNome());
			a.setRaca(obj.getRaca());
			return repo.save(a);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao atualizar animal.");
		}
	}
	
	public void delete(String nome, String raca) {
		Animal obj = findByNomeOrRaca(nome,raca);
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Animal.");
		}
	}
	
	public void verificaNomeOuRacaCadastrado(String nome, String raca) {
		List<Animal> result = repo.findByNomeOrRaca(nome, raca);
		if(!result.isEmpty()) {
			throw new RuntimeException("Nome ou Raça já cadastrado.");
		}
	}
}
