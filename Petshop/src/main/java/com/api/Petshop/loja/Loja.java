package com.api.Petshop.loja;
import com.api.Petshop.endereco.*;
import com.api.Petshop.funcionario.*;
import com.api.Petshop.petshop.Petshop;
import com.api.Petshop.produto.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
public class Loja implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;
	
	@Column(length=50)
	@NotNull(message = "Nome obrigatorio")
	private String nomeGerente;

	@Embedded
	@NotNull(message = "Endereço obrigatorio")
	@Valid
	private Endereco endereco;

	@Column(length = 14, nullable = false)
	@NotBlank(message = "Numero de telefone obrigatorio")
	@Length(min = 13, max = 14, message = "Telefone deve ter 13 ou 14 caracteres (Ex.: (99)9999-9999 ou (99)99999-9999")
	private String telefone;
	
	@JsonManagedReference
	@OneToMany
	@JoinColumn(name = "loja_codigo")
	@NotNull(message = "Loja deve ter no minimo 1 funcionario")
	@Valid
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@JsonManagedReference
	@OneToMany
	@JoinColumn(name = "loja_codigo")
	@NotNull(message = "Loja deve ter no minimo 1 produto")
	@Valid
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@JsonBackReference
	@ManyToOne
	public Petshop petshop;
	
	public long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNomeGerente() {
		return nomeGerente;
	}
	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Petshop getPetshop() {
		return petshop;
	}

	public void setPetshop(Petshop petshop) {
		this.petshop = petshop;
	}
	
	
}
