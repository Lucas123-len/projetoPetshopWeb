package com.api.Petshop.servico;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.List;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.swing.JOptionPane;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.api.Petshop.imprimeDados.*;
import com.api.Petshop.pagamento.*;
import com.api.Petshop.funcionario.*;
import com.api.Petshop.cliente.*;
@Entity
public class Servico implements Pagamento,ImprimeDados{
	
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
	
	@ManyToMany(mappedBy="servicos")
	@Valid
	private List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	@ManyToMany(mappedBy="servicos")
	@Valid
	private List<Cliente> clientes = new ArrayList<Cliente>();
	
	public Servico(String descricao,String tipo,double valor,String dataServico) {
		this.descricao=descricao;
		this.tipo=tipo;
		this.valor=valor;
		this.dataServico=dataServico;
	}
	
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
	public boolean equals(Object obj){
		//if(this == obj){return true;}
		if(!(obj instanceof Servico)){return false;}
		//if(this.getClass != obj.getClass){return false;}
		Servico s = (Servico) obj;
		if(descricao.equals(null)){
			if(!s.descricao.equals(null)){
				return false;
			}
		}
		if(tipo.equals(null)){
			if(!s.tipo.equals(null)){
				return false;
			}
		}
		if(dataServico.equals(null)){
			if(!s.dataServico.equals(null)){
				return false;
			}
		}
		return this.codigo == s.codigo && this.descricao.equals(s.descricao) && this.tipo.equals(s.tipo) && this.valor == s.valor && this.dataServico.equals(s.dataServico);
	}
	@Override
	public int hashCode(){
		return (int)(this.valor * 10 + this.descricao.length());
	}
}
