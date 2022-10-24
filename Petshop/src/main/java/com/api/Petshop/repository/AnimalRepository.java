package com.api.Petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.Petshop.animal.Animal;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>{
	@Query("Select a From Animal a where a.nome = :nome Or a.raca = :raca")
	public List<Animal> findByNomeOrRaca(@Param("nome") String nome, @Param("raca") String raca);
}
