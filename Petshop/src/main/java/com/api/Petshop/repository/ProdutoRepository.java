package com.api.Petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.api.Petshop.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	@Query("Select pd From Produto pd where pd.marca = :marca Or pd.codigo = :codigo")
	public List<Produto> findByMarca(@Param("marca") String marca, @Param("codigo") long codigo);
}
