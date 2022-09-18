package com.api.Petshop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.api.Petshop.animal.Animal;
import com.api.Petshop.cliente.Cliente;
import com.api.Petshop.endereco.Endereco;
import com.api.Petshop.funcao.Funcao;
import com.api.Petshop.funcionario.Funcionario;
import com.api.Petshop.loja.Loja;
import com.api.Petshop.petshop.Petshop;
import com.api.Petshop.produto.Produto;
import com.api.Petshop.repository.AnimalRepository;
import com.api.Petshop.repository.ClienteRepository;
import com.api.Petshop.repository.FuncaoRepository;
import com.api.Petshop.repository.FuncionarioRepository;
import com.api.Petshop.repository.LojaRepository;
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
		
		//Funcionario
		Funcionario fc1 = new Funcionario();
		fc1.setCpf("671.442.130-32");
		fc1.setNome("Paulo");
		fc1.setTelefone("(11)98355-0432");
		
		Endereco end2 = new Endereco();
		end2.setPais("Brasil");
		end2.setEstado("São Paulo");
		end2.setCidade("Santos");
		end2.setBairro("Vila Belmiro");
		end2.setRua("Rua Orivaldo de Souza Rocha");
		end2.setNumero("26");
		
		fc1.setEndereco(end2);
		
		cl.setFuncionarios(List.of(fc1));
		
		//Animal
		Animal an1 = new Animal();
		an1.setNome("Thor");
		an1.setRaca("Labrador");
		an1.setEspecie("cachorro");

		cl.setAnimais(List.of(an1));
		an1.setCliente(cl);
		animalRt.save(an1);
		
		//Funcao
		Funcao fn = new Funcao();
		fn.setDescricao("Atendente");
		fn.setSalario(1200);
		
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
		
		//Petshop
		Petshop pt = new Petshop();
		pt.setCnpj("25.579.865/0001-85");
		pt.setNome("PetMania");
		pt.setSite("www.PetMania.com.br");
		
		fn.setFuncionarios(List.of(fc1));
		fn.setPetshop(pt);
		funcaoRt.save(fn);
		
		//Produto
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
		
		cl.setProdutos(List.of(pr1));
		
		lj.setFuncionarios(List.of(fc1));
		lj.setProdutos(List.of(pr1));
		lojaRt.save(lj);
		
		pt.setLojas(List.of(lj));
		petshopRt.save(pt);
		
		//Servico
		Servico sv1 = new Servico();
		sv1.setDescricao("Banho e Tosa");
		sv1.setTipo("Banho e Tosa");
		sv1.setValor(30);
		sv1.setDataServico("10/09/2022");
		
		fc1.setClientes(List.of(cl));
		fc1.setServicos(List.of(sv1));
		fc1.setFuncao(fn);
		fc1.setLoja(lj);
		funcionarioRt.save(fc1);
		
		cl.setServicos(List.of(sv1));
		
		sv1.setClientes(List.of(cl));
		sv1.setFuncionarios(List.of(fc1));
		
		servicoRt.save(sv1);
		
		clienteRt.save(cl);//salva o repositório no banco
		servicoRt.save(sv1);
		funcionarioRt.save(fc1);
		petshopRt.save(pt);
		produtoRt.save(pr1);
		animalRt.save(an1);
		//Listas dos Objetos
		//Lista de Clientes
		/*List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cl);
		//Lista de Animais
		List<Animal> animais = new ArrayList<Animal>();
		animais.add(an);
		//Lista de Funcionarios
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		funcionarios.add(fc);
		//Lista de Produtos
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(pr);
		//Lista de Servicos
		List<Servico> servicos = new ArrayList<Servico>();
		servicos.add(sv);
		//Lista de Lojas
		List<Loja> lojas = new ArrayList<Loja>();
		lojas.add(lj);*/
		
		
		
		//Validações Serviços
		sv1.setClientes(List.of(cl));
		sv1.setFuncionarios(List.of(fc1));
	}

}
