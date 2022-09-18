package com.api.Petshop.repository;
import com.api.Petshop.cliente.*;
import com.api.Petshop.pessoa.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	@Query("Select p From Pessoa p Where p.cpf = :cpf Or p.nome = :nome")
	public List<Pessoa> findByCpfOrNome(@Param("cpf") String cpf, @Param("nome") String nome);
}
