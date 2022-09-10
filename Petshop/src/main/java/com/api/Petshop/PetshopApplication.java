package com.api.Petshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.Petshop.cliente.Cliente;
import com.api.Petshop.endereco.Endereco;
import com.api.Petshop.funcionario.Funcionario;
import com.api.Petshop.repository.ClienteRepository;
import com.api.Petshop.repository.FuncionarioRepository;

@SpringBootApplication
public class PetshopApplication implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRt;
	
	@Autowired
	private FuncionarioRepository funcionarioRt;

	public static void main(String[] args) {
		SpringApplication.run(PetshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//Cliente
		Cliente cl = new Cliente();
		cl.setCpf("274.200.840-33");
		cl.setNome("Fábio");
		cl.setTelefone("(22)99900-2369");
		
		Endereco end = new Endereco();
		end.setPais("Brasil");
		end.setEstado("Rio de Janeiro");
		end.setCidade("Campos dos Goytacazes");
		end.setBairro("Santa Rosa");
		end.setRua("Rua São Carmmo");
		end.setNumero("14");
		
		cl.setEndereco(end);
		
		clienteRt.save(cl);//salva o repositório no banco
		
		//Funcionario
		Funcionario fc = new Funcionario();
		fc.setCpf("671.442.130-32");
		fc.setNome("Paulo");
		fc.setTelefone("(11)98355-0432");
		
		Endereco end2 = new Endereco();
		end2.setPais("Brasil");
		end2.setEstado("São Paulo");
		end2.setCidade("Santos");
		end2.setBairro("Vila Belmiro");
		end2.setRua("Rua Orivaldo de Souza Rocha");
		end2.setNumero("26");
		
		fc.setEndereco(end2);
		
		funcionarioRt.save(fc);
	}

}
