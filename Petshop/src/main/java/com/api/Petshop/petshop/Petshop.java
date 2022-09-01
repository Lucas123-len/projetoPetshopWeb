package com.api.Petshop.petshop;
import com.api.Petshop.loja.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Petshop {
	@Id
	@Column(length=50, nullable = false, unique = true, updatable = false)
	@CNPJ(message = "CNPJ inválido")
	private String cnpj;

	@Column(length=50)
	@NotBlank(message = "Nome petshop obrigatorio")
	private String nome;

	@Column(length=50)
	@NotBlank(message = "Nome site obrigatorio")
	private String site;
	
	@OneToMany
	@JoinColumn(nullable = false, name = "petshop_cnpj")
	private List<Loja> lojas;
	
	public Petshop(String cnpj, String nome, String site, List<Loja> lojas) {
		this.cnpj = cnpj;
		this.nome = nome;
		this.site = site;
		this.lojas = new ArrayList<Loja>();
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
	public List<Loja> getLojas() {
		return lojas;
	}
	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}
}
