package com.api.Petshop.servico;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.swing.JOptionPane;
import com.api.Petshop.imprimeDados.*;
import com.api.Petshop.pagamento.*;
@Entity
public class Servico implements Pagamento,ImprimeDados{
	@Id
	private String codigo;
	private String descricao;
	private String tipo;
	private double valor;
	private String dataServico;
	public Servico(String codigo,String descricao,String tipo,double valor,String dataServico) {
		this.codigo=codigo;
		this.descricao=descricao;
		this.tipo=tipo;
		this.valor=valor;
		this.dataServico=dataServico;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
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
}
