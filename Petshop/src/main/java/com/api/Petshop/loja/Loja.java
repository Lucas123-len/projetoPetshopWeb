package com.api.Petshop.loja;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Loja {
	
	@Id
	private String codigo;
	
	private String nomeGerente;
	
	private String endereco;
	
	private String telefone;
	
	public Loja(String codigo, String nome, String endereco, String telefone) {
		this.codigo = codigo;
		this.nomeGerente = nome;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNomeGerente() {
		return nomeGerente;
	}
	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
