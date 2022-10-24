package com.api.Petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.Petshop.servico.Servico;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{
	@Query("Select s From Servico s where s.descricao = :descricao Or s.tipo = :tipo")
	public List<Servico> findByDescricaoOrTipo(@Param("descricao") String descricao, @Param("tipo") String tipo);
}
