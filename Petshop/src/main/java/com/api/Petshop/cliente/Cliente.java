package com.api.Petshop.cliente;
import com.api.Petshop.pessoa.*;
import com.api.Petshop.animal.*;
import com.api.Petshop.funcionario.*;
import com.api.Petshop.servico.*;
import com.api.Petshop.produto.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa{
	@OneToMany
	@JoinColumn(name="codigoCliente")
	private List<Animal> animais;
	@OneToMany
	@JoinColumn(name="codigoCliente")
	private List<Produto> produtos;
	@ManyToMany(mappedBy="clientes")
	private List<Funcionario> funcionarios;
	@ManyToMany
	@JoinTable(name="Requisita",
	   		   joinColumns=@JoinColumn(name="codigoCliente"),
	   		   inverseJoinColumns = @JoinColumn(name="codigoServico"))
	private List<Servico> servicos;
	public Cliente(int codigo, String cpf, String nome, String telefone, String pais, String estado, String cidade, String bairro, String rua, String numero, List<Animal> animais, List<Funcionario> funcionarios, List<Servico> servicos, List<Produto> produtos) {
		super(codigo,cpf,nome,telefone,pais,estado,cidade,bairro,rua,numero);
		this.animais = new ArrayList<Animal>();
		this.funcionarios = new ArrayList<Funcionario>();
		this.servicos = new ArrayList<Servico>();
		this.produtos = new ArrayList<Produto>();
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
		return this.nome.length() * 3 + this.codigo;
	}
}
