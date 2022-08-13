package com.api.Petshop.loja;
import com.api.Petshop.endereco.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Loja {
	
	@Id
	private int codigo;
	
	private String nomeGerente;
	
	private Endereco endereco;
	
	private String telefone;
	
	public Loja(int codigo, String nome, String pais, String estado, String cidade, String bairro, String numero, String telefone) {
		this.codigo = codigo;
		this.nomeGerente = nome;
		this.telefone = telefone;
		Endereco ed = new Endereco(pais,estado,cidade,bairro,numero);
		this.endereco = ed;
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
	
}
