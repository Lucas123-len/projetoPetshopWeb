package com.api.Petshop.servico;
import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;
import java.util.Objects;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.swing.JOptionPane;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.api.Petshop.imprimeDados.*;
import com.api.Petshop.pagamento.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.api.Petshop.funcionario.*;
import com.api.Petshop.cliente.*;
@Entity
public class Servico implements Serializable,Pagamento,ImprimeDados{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;
	
	@Column(length=50)
	@NotNull(message = "Descricao Serviço obrigatorio")
	private String descricao;
	
	@Column(length=50)
	@NotNull(message = "Tipo Serviço obrigatorio")
	private String tipo;
	
	private double valor;
	
	@Column(length=50)
	private String dataServico;
	
	@JsonBackReference
	@ManyToMany(mappedBy="servicos")
	@Valid
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@JsonBackReference
	@ManyToMany(mappedBy="servicos")
	@Valid
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDataServico() {
		return dataServico;
	}
	public void setDataServico(String dataServico) {
		this.dataServico = dataServico;
	}
	public double getTotalPagamento() {
		return this.valor;
	}
	public void imprimeDados() {
		JOptionPane.showMessageDialog(null, "Informações do Serviço:"+
				"\nCódigo: "+this.codigo+
				"\nDescrição: "+this.descricao+
				"\nTipo: "+this.tipo+
				"\nValor: "+this.valor+
				"\nData do Serviço: "+this.dataServico+
				"\nTotal do Pagamento: "+this.getTotalPagamento());
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientes, codigo, dataServico, descricao, funcionarios, tipo, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servico other = (Servico) obj;
		return Objects.equals(clientes, other.clientes) && codigo == other.codigo
				&& Objects.equals(dataServico, other.dataServico) && Objects.equals(descricao, other.descricao)
				&& Objects.equals(funcionarios, other.funcionarios) && Objects.equals(tipo, other.tipo)
				&& Double.doubleToLongBits(valor) == Double.doubleToLongBits(other.valor);
	}
	
}
