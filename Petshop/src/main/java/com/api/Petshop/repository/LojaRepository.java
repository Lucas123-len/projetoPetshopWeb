package com.api.Petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.Petshop.loja.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Long>{
	@Query("Select l From Loja l Where l.nomeGerente = :nomeGerente Or l.telefone = :telefone")
	public List<Loja> findByNomeGerenteOrTelefone(@Param("nomeGerente") String nomeGerente, @Param("telefone") String telefone);
}
