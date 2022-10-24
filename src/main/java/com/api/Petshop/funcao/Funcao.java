package com.api.Petshop.funcao;
import com.api.Petshop.funcionario.*;
import com.api.Petshop.petshop.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Funcao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;
	
	@Column(length=50)
	private String descricao;
	
	private float salario;
	
	@JsonManagedReference
	@OneToMany
	@JoinColumn(name = "funcao_codigo")
	@NotNull(message = "Uma função pelo menos 1 funcionario")
	@Valid
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@JsonBackReference
	@ManyToOne
	private Petshop petshop;
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(long codigo) {
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

	public Petshop getPetshop() {
		return petshop;
	}

	public void setPetshop(Petshop petshop) {
		this.petshop = petshop;
	}
	
}
