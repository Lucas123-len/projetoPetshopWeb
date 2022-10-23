package com.api.Petshop;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.api.Petshop.animal.Animal;
import com.api.Petshop.cliente.Cliente;
import com.api.Petshop.endereco.Endereco;
import com.api.Petshop.funcao.Funcao;
import com.api.Petshop.funcionario.Funcionario;
import com.api.Petshop.loja.Loja;
import com.api.Petshop.permissao.Permissao;
import com.api.Petshop.petshop.Petshop;
import com.api.Petshop.produto.Produto;
import com.api.Petshop.repository.AnimalRepository;
import com.api.Petshop.repository.ClienteRepository;
import com.api.Petshop.repository.FuncaoRepository;
import com.api.Petshop.repository.FuncionarioRepository;
import com.api.Petshop.repository.LojaRepository;
import com.api.Petshop.repository.PermissaoRepository;
import com.api.Petshop.repository.PetshopRepository;
import com.api.Petshop.repository.ProdutoRepository;
import com.api.Petshop.repository.ServicoRepository;
import com.api.Petshop.servico.Servico;

@SpringBootApplication
public class PetshopApplication implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRt;
	
	@Autowired
	private FuncionarioRepository funcionarioRt;
	
	@Autowired
	private AnimalRepository animalRt;
	
	@Autowired
	private FuncaoRepository funcaoRt;
	
	@Autowired
	private LojaRepository lojaRt;
	
	@Autowired
	private PetshopRepository petshopRt;
	
	@Autowired
	private ProdutoRepository produtoRt;
	
	@Autowired
	private ServicoRepository servicoRt;
	
	@Autowired
	private PermissaoRepository permissaoRt;

	public static void main(String[] args) {
		SpringApplication.run(PetshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Permissao
		Permissao p1 = new Permissao();
		p1.setNome("ADMIN");
		permissaoRt.save(p1);
		
		Permissao p2 = new Permissao();
		p2.setNome("FUNC");
		permissaoRt.save(p2);
		
		//Petshop
		Petshop pt = new Petshop();
		pt.setCnpj("25.579.865/0001-85");
		pt.setNome("PetMania");
		pt.setSite("www.PetMania.com.br");
		petshopRt.save(pt);
		
		//Loja
		Loja lj = new Loja();
		lj.setNomeGerente("Ricardo");
		lj.setTelefone("(22)99946-3233");
		
		Endereco end3 = new Endereco();
		end3.setPais("Brasil");
		end3.setEstado("Rio de Janeiro");
		end3.setCidade("Campos dos Goytacazes");
		end3.setBairro("Alphaville");
		end3.setRua("Rua Santo Antonio");
		end3.setNumero("113");
		
		lj.setEndereco(end3);
		lojaRt.save(lj);
		
		pt.setLojas(List.of(lj));
		petshopRt.save(pt);
		
		lj.setPetshop(pt);
		lojaRt.save(lj);
		
		//Funcao
		Funcao fn = new Funcao();
		fn.setDescricao("Atendente");
		fn.setSalario(1200);
		fn.setPetshop(pt);
		funcaoRt.save(fn);
		
		//Cliente
		Cliente cl = new Cliente();
		cl.setEmail("fabio@hotmail.com");
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
		clienteRt.save(cl);
		
		//Funcionario
		Funcionario fc1 = new Funcionario();
		fc1.setPermissoes(List.of(p1));
		fc1.setEmail("paulo@gmail.com");
		fc1.setCpf("671.442.130-32");
		fc1.setNome("Paulo");
		fc1.setTelefone("(11)98355-0432");
		fc1.setSenha(new BCryptPasswordEncoder().encode("12345678"));
		
		Endereco end2 = new Endereco();
		end2.setPais("Brasil");
		end2.setEstado("São Paulo");
		end2.setCidade("Santos");
		end2.setBairro("Vila Belmiro");
		end2.setRua("Rua Orivaldo de Souza Rocha");
		end2.setNumero("26");
		
		fc1.setEndereco(end2);
		funcionarioRt.save(fc1);
		
		fn.setFuncionarios(List.of(fc1));
		funcaoRt.save(fn);
		
		lj.setFuncionarios(List.of(fc1));
		lojaRt.save(lj);
		
		cl.setFuncionarios(List.of(fc1));
		clienteRt.save(cl);
		
		//Animal
		Animal an1 = new Animal();
		an1.setNome("Thor");
		an1.setRaca("Labrador");
		an1.setEspecie("cachorro");

		cl.setAnimais(List.of(an1));
		an1.setCliente(cl);
		animalRt.save(an1);
		
		clienteRt.save(cl);
		
		//Produto1
		Produto pr1 = new Produto();
		pr1.setMarca("PetClean");
		pr1.setTipo("Sabonete");
		pr1.setPeso(0.150);
		pr1.setQuantidade(1);
		pr1.setValor(11.50);
		pr1.setDataVenda("20/08/2022");
		pr1.setCliente(cl);
		pr1.setLoja(lj);
		
		produtoRt.save(pr1);
		
		//Servico
		Servico sv1 = new Servico();
		sv1.setDescricao("Banho");
		sv1.setTipo("Banho");
		sv1.setValor(30.00);
		sv1.setDataServico("10/09/2022");
		servicoRt.save(sv1);
		
		Servico sv2 = new Servico();
		sv2.setDescricao("Tosa");
		sv2.setTipo("Tosa");
		sv2.setValor(20.00);
		sv2.setDataServico("10/09/2022");
		servicoRt.save(sv2);
		
		//Produto2
		Produto pr2 = new Produto();
		pr2.setMarca("PetClean");
		pr2.setTipo("Shampoo");
		pr2.setPeso(0.250);
		pr2.setQuantidade(2);
		pr2.setValor(15.50);
		pr2.setDataVenda("20/08/2022");
		pr2.setCliente(cl);
		pr2.setLoja(lj);
		
		produtoRt.save(pr2);
		
		cl.setProdutos(List.of(pr1,pr2));
		clienteRt.save(cl);
		
		lj.setProdutos(List.of(pr1,pr2));
		lojaRt.save(lj);
			
		fc1.setClientes(List.of(cl));
		fc1.setServicos(List.of(sv1));
		fc1.setFuncao(fn);
		fc1.setLoja(lj);
		funcionarioRt.save(fc1);
		
		cl.setServicos(List.of(sv1));
		
		clienteRt.save(cl);//salva o repositório no banco
		
		sv1.setClientes(List.of(cl));
		sv1.setFuncionarios(List.of(fc1));
		servicoRt.save(sv1);
		
		sv2.setClientes(List.of(cl));
		sv2.setFuncionarios(List.of(fc1));
		servicoRt.save(sv2);
	}

}
