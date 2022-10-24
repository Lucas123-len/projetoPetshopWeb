package com.api.Petshop.produto;
import com.api.Petshop.imprimeDados.*;
import com.api.Petshop.pagamento.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.api.Petshop.cliente.*;
import com.api.Petshop.loja.*;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.swing.JOptionPane;
import javax.validation.constraints.NotNull;
@Entity
public class Produto implements Pagamento,ImprimeDados,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long codigo;

	@Column(length=50)
	@NotNull(message = "Marca obrigatorio")
	private String marca;
	
	private double peso;

	@Column(length=50)
	@NotNull(message = "Tipo obrigatorio")
	private String tipo;
	
	private double valor;

	@Column(length=50)
	private String dataVenda;
	
	private int quantidade;
	
	@JsonBackReference
	@ManyToOne
	private Cliente cliente;
	
	@JsonBackReference
	@ManyToOne
	private Loja loja;
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
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
	public String getDataVenda() {
		return dataVenda;
	}
	public void setDataVenda(String dataVenda) {
		this.dataVenda = dataVenda;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getTotalPagamento() {
		return this.valor*this.quantidade;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Loja getLoja() {
		return loja;
	}
	public void setLoja(Loja loja) {
		this.loja = loja;
	}
	public void imprimeDados() {
		JOptionPane.showMessageDialog(null, "Informações do Produto:"+
				"\nCódigo: "+this.codigo+
				"\nMarca: "+this.marca+
				"\nPeso: "+this.peso+
				"\nTipo: "+this.peso+
				"\nValor: "+this.valor+
				"\nData da Venda: "+this.dataVenda+
				"\nQuantidade do Produto: "+this.quantidade+
				"\nTotal do Pagamento: "+this.getTotalPagamento());
	}
	@Override
	public boolean equals(Object obj){
	//if(this == obj){return true;}
	if(!(obj instanceof Produto)){return false;}
	//if(this.getClass != obj.getClass){return false;}
	Produto p = (Produto) obj;
	if(marca.equals(null)){
		if(!p.marca.equals(null)){
			return false;
		}
	}
	if(tipo.equals(null)){
		if(!p.tipo.equals(null)){
			return false;
		}
	}
	if(dataVenda.equals(null)){
		if(!p.dataVenda.equals(null)){
			return false;
		}
	}
	return this.codigo == p.codigo && this.marca.equals(p.marca) && this.peso == p.peso && this.tipo.equals(p.tipo) && this.valor == p.valor && this.dataVenda.equals(p.dataVenda) && this.quantidade == p.quantidade;
	}
	@Override
	public int hashCode(){
		return (int)(this.valor * 10 + this.marca.length());
	}
	
}
