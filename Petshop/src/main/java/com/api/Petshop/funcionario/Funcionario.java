package com.api.Petshop.funcionario;
import com.api.Petshop.pessoa.*;

import javax.persistence.Entity;
import javax.swing.JOptionPane;
@Entity
public class Funcionario extends Pessoa{
	private int codigoFuncao;
	private int codigoLoja;
	public Funcionario(int codigo, int codigoFuncao, int codigoLoja, String cpf, String nome, String pais, String estado, String cidade, String bairro, String rua, String numero) {
		super(codigo,cpf,nome,pais,estado,cidade,bairro,rua,numero);
		this.codigoFuncao = codigoFuncao;
		this.codigoLoja = codigoLoja;
	}
	
	public int getCodigoFuncao() {
		return codigoFuncao;
	}

	public void setCodigoFuncao(int codigoFuncao) {
		this.codigoFuncao = codigoFuncao;
	}

	public int getCodigoLoja() {
		return codigoLoja;
	}

	public void setCodigoLoja(int codigoLoja) {
		this.codigoLoja = codigoLoja;
	}

	public void imprimeDados() {
		JOptionPane.showMessageDialog(null, "Informações do Funcionario:"+
				"\nCódigo Funcionário: "+this.codigo+
				"\nNome: "+this.nome+
				"\nCPF: "+this.cpf+
				"\nEndereço: "+this.endereco.getRua()+","+this.endereco.getNumero()+","+this.endereco.getBairro()+","+this.endereco.getCidade()+","+this.endereco.getEstado()+","+this.endereco.getPais()+
				"\nCódigo Função: "+this.codigoFuncao+
				"\nCódigo Loja: "+this.codigoLoja);
	}
	@Override
	public boolean equals(Object obj){
	//if(this == obj){return true;}
		if(!(obj instanceof Funcionario)){
			return false;
		}
		//if(this.getClass != obj.getClass){return false;}
		Funcionario fc = (Funcionario) obj;
		return this.codigo == fc.codigo;
	}
	@Override
	public int hashCode(){
		return this.getNome().length() * 3 + this.codigo;
	}
}
