package com.api.Petshop.produto;
import com.api.Petshop.imprimeDados.*;
import com.api.Petshop.pagamento.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.JOptionPane;
@Entity
public class Produto implements Pagamento,ImprimeDados{
	@Id
	private String codigo;
	private String marca;
	private double peso;
	private String tipo;
	private double valor;
	private String dataVenda;
	private int quantidade;
	public Produto(String codigo,String marca,double peso,String tipo,double valor,String data,int quantidade) {
		this.codigo=codigo;
		this.marca=marca;
		this.peso=peso;
		this.tipo=tipo;
		this.valor=valor;
		this.dataVenda=data;
		this.quantidade=quantidade;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
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
	
}
