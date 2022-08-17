package com.api.Petshop.pessoa;
import com.api.Petshop.endereco.*;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Pessoa {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int codigo;
	
	@Column(length=50)
	protected String cpf;

	@Column(length=50)
	protected String nome;
	@Embedded
	protected Endereco endereco;
	
	public Pessoa(int codigo, String cpf, String nome, String pais, String estado, String cidade, String bairro, String rua, String numero) {
		this.codigo = codigo;
		this.cpf = cpf;
		this.nome = nome;
		Endereco ed = new Endereco(pais,estado,cidade,bairro,rua,numero);
		this.endereco = ed;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public abstract void imprimeDados();
}
