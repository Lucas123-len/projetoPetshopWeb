package com.api.Petshop.pessoa;
import com.api.Petshop.endereco.*;

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

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.lang.NonNull;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected int codigo;
	
	@Column(length=50)
	@CPF(message = "CPF inválido")
	protected String cpf;

	@Column(length=50)
	@NotBlank(message = "Nome obrigatorio")
	protected String nome;
	
	@Column(length=50)
	protected String telefone;
	
	@Embedded
	@NotNull(message = "Endereço obrigatorio")
	@Valid
	protected Endereco endereco;
	
	public Pessoa(int codigo, String cpf, String nome, String telefone, String pais, String estado, String cidade, String bairro, String rua, String numero) {
		this.codigo = codigo;
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

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
