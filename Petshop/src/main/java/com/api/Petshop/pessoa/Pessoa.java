package com.api.Petshop.pessoa;
import com.api.Petshop.endereco.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Pessoa {
	@Id
	protected String cpf;
	
	protected String nome;
	
	protected Endereco endereco;
	
	public Pessoa(String cpf, String nome, String pais, String estado, String cidade, String bairro, String numero) {
		this.cpf = cpf;
		this.nome = nome;
		Endereco ed = new Endereco(pais,estado,cidade,bairro,numero);
		this.endereco = ed;
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
