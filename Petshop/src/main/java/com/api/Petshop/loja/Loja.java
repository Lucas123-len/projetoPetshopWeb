package com.api.Petshop.loja;
import com.api.Petshop.endereco.*;
import com.api.Petshop.funcionario.*;
import com.api.Petshop.produto.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

@Entity
public class Loja {
	
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
	
	@JsonBackReference
	@OneToMany
	@JoinColumn(name = "loja_codigo")
	@Size(min = 1, message = "Loja deve ter no minimo 1 funcionario")
	@Valid
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@JsonBackReference
	@OneToMany
	@JoinColumn(name = "loja_codigo")
	@Size(min = 1, message = "Loja deve ter no minimo 1 produto")
	@Valid
	private List<Produto> produtos = new ArrayList<Produto>();
	
	
	public Loja(String nome, String pais, String estado, String cidade, String bairro, String rua, String numero, String telefone) {
		this.nomeGerente = nome;
		this.telefone = telefone;
		this.telefone = telefone;
		this.endereco.setPais(pais);
		this.endereco.setEstado(estado);
		this.endereco.setCidade(cidade);
		this.endereco.setBairro(bairro);
		this.endereco.setRua(rua);
		this.endereco.setNumero(numero);
	}
	
	public long getCodigo() {
		return codigo;
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
	
}
