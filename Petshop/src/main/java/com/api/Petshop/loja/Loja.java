package com.api.Petshop.loja;
import com.api.Petshop.endereco.*;
import com.api.Petshop.funcionario.*;
import com.api.Petshop.produto.*;
import com.api.Petshop.petshop.*;

import java.util.ArrayList;
import java.util.Collections;
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

@Entity
public class Loja {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codigo;
	
	@Column(length=50)
	private String nomeGerente;

	@Embedded
	private Endereco endereco;

	@Column(length=50)
	private String telefone;
	
	@OneToMany(mappedBy = "loja")
	private List<Funcionario> funcionarios;
	
	@OneToMany(mappedBy = "loja")
	private List<Produto> produtos;
	
	@ManyToOne
	private Petshop petshop;
	
	public Loja(int codigo, String nome, String pais, String estado, String cidade, String bairro, String rua, String numero, String telefone, List<Funcionario> funcionarios, List<Produto> produtos) {
		this.codigo = codigo;
		this.nomeGerente = nome;
		this.telefone = telefone;
		Endereco ed = new Endereco(pais,estado,cidade,bairro,rua,numero);
		this.endereco = ed;
		this.funcionarios = new ArrayList<Funcionario>();
		this.produtos = new ArrayList<Produto>();
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
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
	
}
