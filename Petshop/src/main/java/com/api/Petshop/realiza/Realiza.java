package com.api.Petshop.realiza;
import com.api.Petshop.funcionario.*;
import com.api.Petshop.servico.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Realiza {
	@Id
	private String codigo;
	private Funcionario funcionario;
	private Servico servico;
	public Realiza(String codigo,String codigoFuncionario,String cpf, String nome, String endereco, String codigoFuncaoFuncionario, String desc, float salario,String codigoServico,String descricao,String tipo,double valor,String dataServico) {
		this.codigo=codigo;
		Funcionario fc=new Funcionario(codigoFuncionario,cpf,nome,endereco,codigoFuncaoFuncionario,desc,salario);
		Servico sr=new Servico(codigoServico,descricao,tipo,valor,dataServico);
		this.funcionario=fc;
		this.servico=sr;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
}
