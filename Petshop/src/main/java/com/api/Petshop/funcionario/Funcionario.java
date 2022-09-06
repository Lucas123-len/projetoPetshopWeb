package com.api.Petshop.funcionario;
import com.api.Petshop.pessoa.*;
import com.api.Petshop.cliente.*;
import com.api.Petshop.servico.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.swing.JOptionPane;
@Entity
public class Funcionario extends Pessoa{
	@ManyToMany
	@JoinTable(name="Atende",
			   joinColumns=@JoinColumn(name="funcionario_codigo"),
			   inverseJoinColumns = @JoinColumn(name="cliente_codigo"))
	private List<Cliente> clientes;
	@ManyToMany
	@JoinTable(name="Realiza",
	   		   joinColumns=@JoinColumn(name="funcionario_codigo"),
	   		   inverseJoinColumns = @JoinColumn(name="servico_codigo"))
	private List<Servico> servicos;
	
	public Funcionario(String cpf, String nome, String telefone, String pais, String estado, String cidade, String bairro, String rua, String numero, List<Cliente> clientes, List<Servico> servicos) {
		super(cpf,nome,telefone,pais,estado,cidade,bairro,rua,numero);
		this.clientes = new ArrayList<Cliente>();
		this.servicos = new ArrayList<Servico>();
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}

	public void imprimeDados() {
		JOptionPane.showMessageDialog(null, "Informações do Funcionario:"+
				"\nCódigo Funcionário: "+this.codigo+
				"\nNome: "+this.nome+
				"\nCPF: "+this.cpf+
				"\nTelefone: "+this.telefone+
				"\nEndereço: "+this.endereco.getRua()+","+this.endereco.getNumero()+","+this.endereco.getBairro()+","+this.endereco.getCidade()+","+this.endereco.getEstado()+","+this.endereco.getPais());
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
