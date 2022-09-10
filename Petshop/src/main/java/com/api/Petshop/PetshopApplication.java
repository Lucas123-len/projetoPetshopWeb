package com.api.Petshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.Petshop.cliente.Cliente;
import com.api.Petshop.repository.ClienteRepository;

@SpringBootApplication
public class PetshopApplication implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRt;

	public static void main(String[] args) {
		SpringApplication.run(PetshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//Cliente
		Cliente cl = new Cliente("274.200.840-33","Fábio","(22)99900-2369","Brasil","Rio de Janeiro","Campos dos Goytacazes","Santa Rosa","Rua São Carmmo","14");
		clienteRt.save(cl);//salva o repositório no banco
	}

}
