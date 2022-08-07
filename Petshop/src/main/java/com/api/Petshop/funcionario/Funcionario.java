package com.api.Petshop.funcionario;
import com.api.Petshop.pessoa.*;
import com.api.Petshop.funcao.*;

import javax.persistence.Entity;

import javax.swing.JOptionPane;
@Entity
public class Funcionario extends Pessoa{
	private String codigo;
	private Funcao funcionarioFunc;
	public Funcionario(String codigo, String cpf, String nome, String endereco, String codigoFuncao, String desc, float salario) {
		super(cpf,nome,endereco);
		Funcao fc = new Funcao(codigoFuncao,desc,salario);
		this.codigo=codigo;
		this.funcionarioFunc = fc;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Funcao getFuncionarioFunc() {
		return funcionarioFunc;
	}

	public void setFuncionarioFun(Funcao funcionarioFunc) {
		this.funcionarioFunc = funcionarioFunc;
	}

	public void imprimeDados() {
		JOptionPane.showMessageDialog(null, "Informações do Funcionario:"+
				"\nCódigo Funcionário: "+this.codigo+
				"\nNome: "+this.nome+
				"\nCPF: "+this.cpf+
				"\nEndereço: "+this.endereco+
				"\nCódigo Função: "+this.funcionarioFunc.getCodigo()+
				"\nDescrição da Função: "+this.funcionarioFunc.getDescricao()+
				"\nSalário: "+this.funcionarioFunc.getSalario());
	}
}
