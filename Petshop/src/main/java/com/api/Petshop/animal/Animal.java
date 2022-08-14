package com.api.Petshop.animal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Animal {
	
	@Id
	private int codigo;
	
	private String nome;
	
	private String raca;
	
	private String especie;
	
	public Animal(int codigo, String nome, String raca, String especie) {
		this.codigo = codigo;
		this.nome = nome;
		this.raca = raca;
		this.especie = especie;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
}
