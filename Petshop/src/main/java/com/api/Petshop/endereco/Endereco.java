package com.api.Petshop.endereco;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class Endereco {
	@Column(length=50)
	private String pais;
	@Column(length=50)
	private String estado;
	@Column(length=50)
	@NotBlank(message = "Cidade obrigatoria")
	@Length(max = 50, message = "cidade deve ter no máximo 50 caracteres.")
	private String cidade;
	@Column(length=50)
	@NotBlank(message = "Bairro obrigatoria")
	@Length(max = 50, message = "bairro deve ter no máximo 50 caracteres.")
	private String bairro;
	@Column(length=200)
	@NotBlank(message = "Rua obrigatoria")
	@Length(max = 200, message = "Rua deve ter no máximo 200 caracteres.")
	private String rua;
	@Column(length=50)
	@Digits(integer = 4, fraction = 0,message = "Numero deve ser inteiro e ter até 4 caracteres")
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
