package com.api.Petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.api.Petshop.funcao.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long>{
	@Query("Select f From Funcao f Where f.descricao = :descricao")
	public List<Funcao> findByDescricao(@Param("descricao") String descricao);
}
