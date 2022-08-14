package com.api.Petshop.endereco;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Endereco {
	@Column(length=50)
	private String pais;
	@Column(length=50)
	private String estado;
	@Column(length=50)
	private String cidade;
	@Column(length=50)
	private String bairro;
	@Column(length=50)
	private String rua;
	@Column(length=50)
	private String numero;
	public Endereco(String pais, String estado, String cidade, String bairro, String rua, String numero) {
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
}
