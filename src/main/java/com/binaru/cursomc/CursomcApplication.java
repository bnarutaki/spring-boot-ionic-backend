package com.binaru.cursomc;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.binaru.cursomc.domain.Categoria;
import com.binaru.cursomc.domain.Cidade;
import com.binaru.cursomc.domain.Cliente;
import com.binaru.cursomc.domain.Endereco;
import com.binaru.cursomc.domain.Estado;
import com.binaru.cursomc.domain.Produto;
import com.binaru.cursomc.domain.enums.TipoCliente;
import com.binaru.cursomc.repositories.CategoriaRepository;
import com.binaru.cursomc.repositories.CidadeRepository;
import com.binaru.cursomc.repositories.ClienteRepository;
import com.binaru.cursomc.repositories.EnderecoRepository;
import com.binaru.cursomc.repositories.EstadoRepository;
import com.binaru.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	@Autowired
	private CategoriaRepository categoriaRepository; 

	@Autowired
	private ProdutoRepository produtoRepository; 
	
	@Autowired
	private EstadoRepository estadoRepository; 

	@Autowired
	private CidadeRepository cidadeRepository; 

	@Autowired
	private ClienteRepository clienteRepository; 

	@Autowired
	private EnderecoRepository enderecoRepository; 
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informatica");
		Categoria c2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 70.00);
		
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		Estado est1 = new Estado(null, "MG");
		Estado est2 = new Estado(null, "SP");

		Cidade cid1 = new Cidade(null,"Uberlandia", est1);
		Cidade cid2 = new Cidade(null,"Sao Paulo", est2);
		Cidade cid3 = new Cidade(null,"Valinhos", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));
		
		Cliente cli1 = new Cliente(null, "maria silva", "aaa@gmail.com", "1231231212", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("1233-1212", "9877-1234"));
				
		Endereco e1 = new Endereco(null,"Rua Flores","300","ap203","Jardim","768768-09",cid1,cli1);
		Endereco e2 = new Endereco(null,"Av Matos","103","sale 122","Centro","2333-09",cid2,cli1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		categoriaRepository.saveAll(Arrays.asList(c1, c2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
	
	
	
}
