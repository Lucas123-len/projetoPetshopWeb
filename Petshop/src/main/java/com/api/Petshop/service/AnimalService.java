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
	
	public Animal findById(Long codigo) {
		Optional<Animal> result = repo.findById(codigo);
		if(result.isEmpty()) {
			throw new RuntimeException("Animal não encontrado.");
		}
		return result.get();
	}
	
	public Animal save(Animal a) {
		try {
			return repo.save(a);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar animal.");
		}
	}
	
	public Animal update(Animal a) {
		try {
			return repo.save(a);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao atualizar Animal.");
		}
	}
	
	public void delete(Long codigo) {
		Animal obj = findById(codigo);
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Animal.");
		}
	}
}
