package com.api.Petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.Petshop.animal.Animal;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Long>{
	@Query("Select a Animal a where a.nome = :nome Or a.raca = :raca")
	public List<Animal> findByNomeOrRaca(@Param("nome") String nome, @Param("raca") String raca);
}
