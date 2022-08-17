package com.api.Petshop.cliente;
import com.api.Petshop.pessoa.*;

import javax.swing.JOptionPane;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Cliente extends Pessoa{
	@Column(length=50)
	private String telefone;
	public Cliente(int codigo, String telefone, String cpf, String nome, String pais, String estado, String cidade, String bairro, String rua, String numero) {
		super(codigo,cpf,nome,pais,estado,cidade,bairro,rua,numero);
		this.telefone = telefone;
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
				"\nTelefone: "+this.telefone+
				"\nEndereço: "+this.endereco.getRua()+","+this.endereco.getNumero()+","+this.endereco.getBairro()+","+this.endereco.getCidade()+","+this.endereco.getEstado()+","+this.endereco.getPais()
				);
	}
	@Override
	public boolean equals(Object obj){
		//if(this == obj){return true;}
		if(!(obj instanceof Cliente)){
			return false;
		}
		//if(this.getClass != obj.getClass){return false;}
		Cliente cl = (Cliente) obj;
		if(telefone.equals(null)){
			if(!cl.telefone.equals(null)){
				return false;
			}
		}
		return this.codigo == cl.codigo && this.telefone.equals(cl.telefone);
	}
	@Override
	public int hashCode(){
		return this.nome.length() * 3 + this.codigo;
	}
}
