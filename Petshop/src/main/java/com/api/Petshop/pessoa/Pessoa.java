package com.api.Petshop.pessoa;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Pessoa {
	@Id
	protected String cpf;
	
	protected String nome;
	
	protected String endereco;
	
	public Pessoa(String cpf, String nome, String endereco) {
		this.cpf = cpf;
		this.nome = nome;
		this.endereco = endereco;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public abstract void imprimeDados();
}
