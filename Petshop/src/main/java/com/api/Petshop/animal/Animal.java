package com.api.Petshop.animal;
import com.api.Petshop.cliente.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Animal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;
	@Column(length=50)
	private String nome;
	@Column(length=50)
	private String raca;
	@Column(length=50)
	private String especie;
	
	@ManyToOne
	private Cliente cliente;
	
	public Animal(String nome, String raca, String especie) {
		this.nome = nome;
		this.raca = raca;
		this.especie = especie;
	}

	public long getCodigo() {
		return codigo;
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
