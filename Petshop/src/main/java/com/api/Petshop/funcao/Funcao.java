package com.api.Petshop.funcao;
import com.api.Petshop.funcionario.*;
import com.api.Petshop.petshop.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@Column(length=50)
	private String descricao;
	
	private float salario;
	
	@JsonBackReference
	@OneToMany
	@JoinColumn(name = "funcao_codigo")
	@Size(min = 1, message = "Uma função pelo menos 1 funcionario")
	@Valid
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@ManyToOne
	private Petshop petshop;
	
	public Funcao(String descricao, float salario) {
		this.descricao = descricao;
		this.salario = salario;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public int getCodigo() {
		return codigo;
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
