package com.api.Petshop.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.api.Petshop.cliente.Cliente;
import com.api.Petshop.exception.NotFoundException;
import com.api.Petshop.pessoa.Pessoa;
import com.api.Petshop.repository.ClienteRepository;
import com.api.Petshop.servico.Servico;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;
	
	public List<Cliente> findAll(int page, int size){
		Pageable p = PageRequest.of(page, size);
		return repo.findAll(p).toList();
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Cliente findById(long codigo) {
		Optional<Cliente> result = repo.findById(codigo);
		if(result.isEmpty()) {
			throw new NotFoundException("Cliente não encontrado.");
		}
		return result.get();
	}
	
	public Cliente save(Cliente c, MultipartFile file) {
		//arquivo
		if(file != null) {
			if(!file.isEmpty()) {
				salvarArquivo(file, c.getCpf()+".pdf");
				c.setDocumentos(c.getCpf()+".pdf");
			}else {
				c.setDocumentos(null);
			}
		}
		
		//verifica se cpf e nome já cadastrados
		verificaCpfEmailCadastrado(c.getCpf(), c.getNome());
		try {
			return repo.save(c);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao salvar o cliente.");
		}
	}
	
	public void salvarArquivo(MultipartFile file, String novoNome) {
		if(file.getContentType().equals(MediaType.APPLICATION_PDF_VALUE)) {
			Path dest = Paths.get("uploads", novoNome);
			try {
				file.transferTo(dest);
			}  catch (IOException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException("Falha ao salvar o arquivo.");
			}
		}else {
			throw new RuntimeException("Arquivo deve ser do tipo PDF.");
		}
	}
	
	public Cliente update(Cliente c, MultipartFile file) {
		
		Cliente obj = findById(c.getCodigo());
		
		//arquivo
		if(file != null) {
			if(!file.isEmpty()) {
				salvarArquivo(file, c.getCpf()+".pdf");
				c.setDocumentos(c.getCpf()+".pdf");
			}else {
				c.setDocumentos(null);
			}
		}
		
		List<Servico> servicosRealizados = obj.getServicos();
		servicosRealizados.removeAll(c.getServicos());
		
		try {
			c.setCpf(obj.getCpf());
			c.setNome(obj.getNome());
			return repo.save(c);
		}catch(Exception e){
			throw new RuntimeException("Falha ao atualizar Cliente.");
		}
	}
	
	public void delete(long codigo) {
		Cliente obj = findById(codigo);
		//verifica se há reservas
		verificaExclusaoCliente(obj.getServicos());
		try {
			repo.delete(obj);
		}catch(Exception e) {
			throw new RuntimeException("Falha ao deletar o Cliente.");
		}
	}
	
	public void verificaCpfEmailCadastrado(String cpf, String email) {
		List<Pessoa> result = repo.findByCpfOrEmail(cpf, email);
		if(!result.isEmpty()) {
			throw new RuntimeException("Cpf ou Email já cadastrado.");
		}
	}
	
	public void verificaExclusaoCliente(List<Servico> servicos) {
		for(Servico s : servicos)
		if(!s.getFuncionarios().isEmpty()) {
			throw new RuntimeException("Cliente requisitou serviços. Não pode ser excluído.");
		}
	}
	
}
