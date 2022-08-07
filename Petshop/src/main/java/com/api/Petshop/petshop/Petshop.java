package com.api.Petshop.petshop;

import javax.persistence.Id;
import javax.persistence.Entity;


@Entity
public class Petshop {
	@Id
	private String cnpj;
	
	private String nome;
	
	private String site;
	
	public Petshop(String cnpj, String nome, String site) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.site = site;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
}
