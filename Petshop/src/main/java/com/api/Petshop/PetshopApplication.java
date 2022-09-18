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
		
		//Animal
		Animal an = new Animal();
		an.setNome("Thor");
		an.setRaca("Labrador");
		an.setEspecie("cachorro");
		
		animalRt.save(an);
		
		//Funcao
		Funcao fn = new Funcao();
		fn.setDescricao("Atendente");
		fn.setSalario(1200);
		
		funcaoRt.save(fn);
		
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
		
		//Petshop
		Petshop pt = new Petshop();
		pt.setCnpj("25.579.865/0001-85");
		pt.setNome("PetMania");
		pt.setSite("www.PetMania.com.br");
		
		petshopRt.save(pt);
		
		//Produto
		Produto pr = new Produto();
		pr.setMarca("PetClean");
		pr.setTipo("Sabonete");
		pr.setPeso(0.150);
		pr.setQuantidade(1);
		pr.setValor(11.50);
		pr.setDataVenda("20/08/2022");
		
		produtoRt.save(pr);
		
		//Servico
		Servico sv = new Servico();
		sv.setDescricao("Banho e Tosa");
		sv.setTipo("Banho e Tosa");
		sv.setValor(30);
		sv.setDataServico("10/09/2022");
		
		servicoRt.save(sv);
		
		//Listas dos Objetos
		//Lista de Clientes
		List<Cliente> clientes = new ArrayList<Cliente>();
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
		lojas.add(lj);
		
		//Validações Cliente
		cl.setProdutos(produtos);
		cl.setAnimais(animais);
		cl.setFuncionarios(funcionarios);
		cl.setServicos(servicos);
		
		//Associando Cliente a Animal
		an.setCliente(cl);
		
		//Validações Funcao
		fn.setFuncionarios(funcionarios);
		fn.setPetshop(pt);
		
		//Validações Funcionario
		fc.setClientes(clientes);
		fc.setServicos(servicos);
		fc.setFuncao(fn);
		fc.setLoja(lj);
		
		//Validações Loja
		lj.setFuncionarios(funcionarios);
		lj.setProdutos(produtos);
		
		pt.setLojas(lojas);
		
		pr.setCliente(cl);
		pr.setLoja(lj);
		
		//Validações Serviços
		sv.setClientes(clientes);
		sv.setFuncionarios(funcionarios);
	}

}
