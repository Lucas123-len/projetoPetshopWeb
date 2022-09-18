package com.api.Petshop.cliente;
import com.api.Petshop.pessoa.*;
import com.api.Petshop.animal.*;
import com.api.Petshop.funcionario.*;
import com.api.Petshop.servico.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.api.Petshop.produto.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
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

	@OneToMany
	@JoinColumn(name = "cliente_codigo")
	@Size(min = 1, message = "Cliente deve ter no minimo 1 animal")
	@Valid
	private List<Animal> animais = new ArrayList<Animal>();
	
	@JsonBackReference
	@OneToMany//(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "cliente_codigo")
	@Size(min = 1, message = "Cliente deve comprar no minimo 1 produto")
	@Valid
	private List<Produto> produtos = new ArrayList<Produto>();
	
	@ManyToMany(mappedBy="clientes")
	@Size(min = 1, message = "Cliente deve ser atendido por pelo menos 1 funcionario")
	@Valid
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
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
	public boolean equals(Object obj){
		//if(this == obj){return true;}
		if(!(obj instanceof Cliente)){
			return false;
		}
		//if(this.getClass != obj.getClass){return false;}
		Cliente cl = (Cliente) obj;
		if(telefone.equals(null)){
			if(!cl.telefone.equals(null)){
				return false;
			}
		}
		return this.codigo == cl.codigo && this.telefone.equals(cl.telefone);
	}
	@Override
	public int hashCode(){
		return (int) (this.nome.length() * 3 + this.codigo);
	}
}
