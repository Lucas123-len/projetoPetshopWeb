package com.api.Petshop.funcionario;
import com.api.Petshop.pessoa.*;
import com.api.Petshop.cliente.*;
import com.api.Petshop.servico.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.api.Petshop.loja.*;
import com.api.Petshop.permissao.Permissao;
import com.api.Petshop.funcao.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.swing.JOptionPane;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
@Entity
public class Funcionario extends Pessoa{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Senha Obrigatoria")
	@Length(min = 8, message = "Senha deve ter no minimo 8 caracteres")
	private String senha;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name="Atende",
			   joinColumns=@JoinColumn(name="funcionario_codigo"),
			   inverseJoinColumns = @JoinColumn(name="cliente_codigo"))
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	@JsonManagedReference
	@ManyToMany
	@JoinTable(name="Realiza",
	   		   joinColumns=@JoinColumn(name="funcionario_codigo"),
	   		   inverseJoinColumns = @JoinColumn(name="servico_codigo"))
	private List<Servico> servicos = new ArrayList<Servico>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@NotNull(message = "Funcionario deve ter no mínimo 1 permissao.")
	private List<Permissao> permissoes = new ArrayList<Permissao>();
	
	@JsonBackReference
	@ManyToOne
	private Loja loja;
	
	@JsonBackReference
	@ManyToOne
	private Funcao funcao;
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public Funcao getFuncao() {
		return funcao;
	}

	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}
	
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void imprimeDados() {
		JOptionPane.showMessageDialog(null, "Informações do Funcionario:"+
				"\nCódigo Funcionário: "+this.codigo+
				"\nNome: "+this.nome+
				"\nCPF: "+this.cpf+
				"\nTelefone: "+this.telefone+
				"\nEndereço: "+this.endereco.getRua()+","+this.endereco.getNumero()+","+this.endereco.getBairro()+","+this.endereco.getCidade()+","+this.endereco.getEstado()+","+this.endereco.getPais());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(senha);
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
		Funcionario other = (Funcionario) obj;
		return Objects.equals(senha, other.senha);
	}
	
}
