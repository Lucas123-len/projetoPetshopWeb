package com.api.Petshop.cliente;
import com.api.Petshop.pessoa.*;
import com.api.Petshop.animal.*;
import com.api.Petshop.funcionario.*;
import com.api.Petshop.servico.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.api.Petshop.produto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(length = 200)
	@Length(max = 200, message = "Documentos devem ter no máximo 200 caracteres.")
	private String documentos;
	
	@JsonManagedReference
	@OneToMany
	@JoinColumn(name = "cliente_codigo")
	@NotNull(message = "Cliente deve ter no minimo 1 animal")
	@Valid
	private List<Animal> animais = new ArrayList<Animal>();
	
	@JsonManagedReference
	@OneToMany//(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cliente_codigo")
	@NotNull(message = "Cliente deve comprar no minimo 1 produto")
	@Valid
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@JsonIgnore
	@ManyToMany(mappedBy="clientes")
	@NotNull(message = "Cliente deve ser atendido por pelo menos 1 funcionario")
	@Valid
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@JsonManagedReference
	@ManyToMany
	@JoinTable(name="Requisita",
	   		   joinColumns=@JoinColumn(name="cliente_codigo"),
	   		   inverseJoinColumns = @JoinColumn(name="servico_codigo"))
	private List<Servico> servicos = new ArrayList<Servico>();
	
	public String getDocumentos() {
		return documentos;
	}

	public void setDocumentos(String documentos) {
		this.documentos = documentos;
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public List<Animal> getAnimais() {
		return animais;
	}

	public void setAnimais(List<Animal> animais) {
		this.animais = animais;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public void imprimeDados() {
		JOptionPane.showMessageDialog(null, "Informações do Cliente:"+
				"\nCódigo Cliente:"+this.codigo+
				"\nNome: "+this.nome+
				"\nCPF: "+this.cpf+
				"\nTelefone: "+this.telefone+
				"\nEndereço: "+this.endereco.getRua()+","+this.endereco.getNumero()+","+this.endereco.getBairro()+","+this.endereco.getCidade()+","+this.endereco.getEstado()+","+this.endereco.getPais()
				);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(documentos);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(documentos, other.documentos);
	}
	
}
