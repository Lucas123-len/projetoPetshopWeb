package com.api.Petshop.realiza;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Realiza {
	@Id
	private int codigo;
	private int codigoFuncionario;
	private int codigoServico;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCodigoFuncionario() {
		return codigoFuncionario;
	}
	public void setCodigoFuncionario(int codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}
	public int getCodigoServico() {
		return codigoServico;
	}
	public void setCodigoServico(int codigoServico) {
		this.codigoServico = codigoServico;
	}
	
}
