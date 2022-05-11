package com.rcamargo15.cursoSpringBoot;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rcamargo15.cursoSpringBoot.domain.Categoria;
import com.rcamargo15.cursoSpringBoot.domain.Cidade;
import com.rcamargo15.cursoSpringBoot.domain.Cliente;
import com.rcamargo15.cursoSpringBoot.domain.Endereco;
import com.rcamargo15.cursoSpringBoot.domain.Estado;
import com.rcamargo15.cursoSpringBoot.domain.Pagamento;
import com.rcamargo15.cursoSpringBoot.domain.PagamentoComBoleto;
import com.rcamargo15.cursoSpringBoot.domain.PagamentoComCartao;
import com.rcamargo15.cursoSpringBoot.domain.Pedido;
import com.rcamargo15.cursoSpringBoot.domain.Produto;
import com.rcamargo15.cursoSpringBoot.domain.enums.EstadoPagamento;
import com.rcamargo15.cursoSpringBoot.domain.enums.TipoCliente;
import com.rcamargo15.cursoSpringBoot.repositories.CategoriaRepository;
import com.rcamargo15.cursoSpringBoot.repositories.CidadeRepository;
import com.rcamargo15.cursoSpringBoot.repositories.ClienteRepository;
import com.rcamargo15.cursoSpringBoot.repositories.EnderecoRepository;
import com.rcamargo15.cursoSpringBoot.repositories.EstadoRepository;
import com.rcamargo15.cursoSpringBoot.repositories.PagamentoRepository;
import com.rcamargo15.cursoSpringBoot.repositories.PedidoRepository;
import com.rcamargo15.cursoSpringBoot.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoSpringBootApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	EstadoRepository estadoRepository;
	
	@Autowired
	CidadeRepository cidadeRepository;
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoSpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		p1.setCategorias(Arrays.asList(cat1));
		p2.setCategorias(Arrays.asList(cat1, cat2));
		p3.setCategorias(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.setCidades(Arrays.asList(c1));
		est2.setCidades(Arrays.asList(c2,c3));

		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378923115", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("45165541","416164587"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "387777012", cli1, c2);
		
		cli1.setEnderecos(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:32"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.setPedidos(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		
		
	}

}
