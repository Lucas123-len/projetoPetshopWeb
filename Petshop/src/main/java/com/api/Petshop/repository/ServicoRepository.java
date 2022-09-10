package com.api.Petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.Petshop.servico.Servico;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long>{
	@Query("Select s Servico s where s.descricao = :descricao Or s.tipo = :tipo")
	private List<Servico> findByDescricaoOrTipo(@Param("descricao") String descricao, @Param("tipo") String tipo) {
		return null;
	}
}
