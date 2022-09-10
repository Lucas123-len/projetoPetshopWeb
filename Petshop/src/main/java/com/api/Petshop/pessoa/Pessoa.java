package com.api.Petshop.pessoa;
import com.api.Petshop.endereco.*;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Pessoa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected long codigo;
	
	@Column(length=14, nullable = false)
	@CPF(message = "CPF inválido")
	protected String cpf;

	@Column(length=50)
	@NotBlank(message = "Nome obrigatorio")
	protected String nome;
	
	@Column(length=14, nullable = false)
	@NotBlank(message = "Numero de telefone obrigatorio")
	@Length(min = 13, max = 14, message = "Telefone deve ter 13 ou 14 caracteres (Ex.: (99)9999-9999 ou (99)99999-9999")
	protected String telefone;
	
	@Embedded
	@NotNull(message = "Endereço obrigatorio")
	@Valid
	protected Endereco endereco;
	
	public Pessoa(String cpf, String nome, String telefone, String pais, String estado, String cidade, String bairro, String rua, String numero) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		Endereco ed = new Endereco(pais,estado,cidade,bairro,rua,numero);
		this.endereco = ed;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public long getCodigo() {
		return codigo;
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
