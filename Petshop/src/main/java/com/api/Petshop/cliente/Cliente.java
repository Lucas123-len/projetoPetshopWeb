package com.api.Petshop.cliente;
import com.api.Petshop.pessoa.*;

import javax.swing.JOptionPane;

import javax.persistence.Entity;

@Entity
public class Cliente extends Pessoa{
	private String codigo;
	private String telefone;
	public Cliente(String codigo, String telefone, String cpf, String nome, String endereco) {
		super(cpf,nome,endereco);
		this.codigo=codigo;
		this.telefone = telefone;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public void imprimeDados() {
		JOptionPane.showMessageDialog(null, "Informações do Cliente:"+
				"\nCódigo Cliente:"+this.codigo+
				"\nNome: "+this.nome+
				"\nCPF: "+this.cpf+
				"\nEndereço: "+this.endereco+
				"\nTelefone: "+this.telefone);
	}
	@Override
	public boolean equals(Object obj){
		//if(this == obj){return true;}
		if(!(obj instanceof Cliente)){
			return false;
		}
		//if(this.getClass != obj.getClass){return false;}
		Cliente cl = (Cliente) obj;
		if(codigo.equals(null)){
			if(!cl.codigo.equals(null)){
				return false;
			}
		}
		if(telefone.equals(null)){
			if(!cl.telefone.equals(null)){
				return false;
			}
		}
		return this.codigo.equals(cl.codigo) && this.telefone.equals(cl.telefone);
	}
	@Override
	public int hashCode(){
		return this.nome.length() * 3;
	}
}
