package com.api.Petshop.funcao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Funcao {
	
	@Id
	private String codigo;
	
	private String descricao;
	
	private float salario;
	
	public Funcao(String codigo, String descricao, float salario) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.salario = salario;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}
}
