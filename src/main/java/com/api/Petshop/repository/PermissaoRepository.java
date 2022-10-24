package com.api.Petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.Petshop.permissao.Permissao;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long>{
	@Query("Select pr from Permissao pr where pr.codigo = :codigo Or pr.nome = :nome")
	public List<Permissao> findByCodigoOrNome(@Param("codigo") long codigo, @Param("nome") String nome);
}
