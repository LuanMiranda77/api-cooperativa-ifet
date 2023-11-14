package com.api.config;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.api.domain.OrderItem;
import com.api.domain.OrderSale;
import com.api.domain.Payment;
import com.api.domain.Product;
import com.api.domain.Setor;
import com.api.domain.UserAplication;
import com.api.domain.enuns.OrderStatus;
import com.api.domain.enuns.StatusUsuario;
import com.api.repository.ClienteRepository;
import com.api.repository.PagamentoRepository;
import com.api.repository.PedidoRepository;
import com.api.repository.ProdutoRepository;
import com.api.repository.SetorRepository;
import com.api.repository.UsuarioRepository;
import com.api.services.PedidoService;
import com.api.services.UsuarioService;
import com.api.utils.UtilsHorasData;

@Configuration
@Profile("prod")
public class ConfigAmbienteDev {

	@Transient
	private int quantDeLoop = 100;

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	UsuarioRepository userRepository;
	@Autowired
	PagamentoRepository agamentoRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	SetorRepository setorRepository;
	@Autowired
	PedidoService pedidoService;
	@Autowired
	UsuarioService userService;


	@Bean
	public void inserindoBanco() {

//		Lista de para teste
		List<Product> produtos = new ArrayList<>();
		List<UserAplication> users = new ArrayList<>();
		List<OrderSale> pedidos = new ArrayList<>();
		List<Setor> setors = new ArrayList<>();

// 		setando dados dos usuarios		
		UserAplication user;
		Product produto;
		OrderSale pedido = null;

		Random gerador = new Random();
		

		Setor setor = new Setor();
		setor.setRazao("Suinos LTDA");
		setor.setNome("Suinos");
		setor.setCnpjCpf("35367939000173");
		setor = setorRepository.save(setor);
		
		setor = new Setor();
		setor.setRazao("Laticinio LTDA");
		setor.setNome("Laticinio");
		setor.setCnpjCpf("35367939000173");
		setor = setorRepository.save(setor);

		UserAplication user1 = new UserAplication();
		user1.setEmail("agilityecommerce@gmail.com");
		user1.setCpf("39926782027");
		user1.setCelular("83996386694");
		;
		user1.setPassword("123456");
		user1.setNome("ADMIN");
		user1.setRoles("1-2-3");
		user1.setStatus(StatusUsuario.S);
//		new BCryptPasswordEncoder().encode("123456")
		user1.setCargo("R");
		user = userRepository.save(user1);

		user = new UserAplication();
		user.setEmail("luanprof30@gmail.com");
		user.setPassword("123456");
		user.setNome("LUAN MIRANDA");
		user.setRoles("1-2-3-4");
//		new BCryptPasswordEncoder().encode("123456")
		user.setStatus(StatusUsuario.S);
		user.setCargo("M");
		user = userRepository.save(user);


		for (int i = 0; i < quantDeLoop; i++) {
			

			UserAplication user3 = new UserAplication();
			user3.setEmail("test" + i + "@gmail.com");
			user3.setCpf("39926782027");
			user3.setCelular("83996386694");
			;
			user3.setPassword("123456");
			user3.setNome("TESTE USER" + i);
			user3.setRoles("1-2-3");
			user3.setStatus(StatusUsuario.S);
//			new BCryptPasswordEncoder().encode("123456")
			user3.setCargo(i == 1 ? "G" : i == 2 ? "C" : "E");
			user3.setSetor(setor);

			users.add(user3);
//			user = userRepository.save(user3);

			BigDecimal b = new BigDecimal(1.8);
			produto = new Product();
			produto.setEan("789654789123");
			produto.setName("item-" + i + 1);
			produto.setCodigo( (long) (i + 1));
			produto.setMeasure("UND");
			produto.setBalance((double) i);
			produtos.add(produto);
			produto.setId(i + 1l);
			produto.setPrice(new BigDecimal(23));

			Payment pagamento = new Payment();

			Date date = UtilsHorasData.subtrair(new Date(), 3);

			if (i % 2 == 0) {
				pagamento.setName("CARTAO CREDITO");

			}

			pedido = new OrderSale();
//			pedido.setCliente(cliente);
			pedido.setDateClose(date);

			if (i % 2 == 0) {
				pedido.setStatus(OrderStatus.FINALIZADO);

			} else {
				pedido.setStatus(OrderStatus.CANCELADO);
			}

			OrderItem itens = new OrderItem();
			itens.setOrder(pedido);
			itens.setProduct(produto);
			itens.setQuantitySale(2);

			OrderItem itens2 = new OrderItem();
			itens.setOrder(pedido);
			itens.setProduct(produto);
			itens.setQuantitySale(10);

			OrderItem itens3 = new OrderItem();
			itens.setOrder(pedido);
			itens.setProduct(produto);
			itens.setQuantitySale(3);

			ArrayList<OrderItem> itensPedido = new ArrayList<OrderItem>();
			itensPedido.add(itens3);
			itensPedido.add(itens2);
			itensPedido.add(itens);

			pedido.setPagamento(pagamento);
			pedido.setProducts(itensPedido);

			pedido.setValorTotal((produto.getPrice().multiply(new BigDecimal(
					itens.getQuantitySale() + itens2.getQuantitySale() + itens3.getQuantitySale()))));

			pedidos.add(pedido);

		}

// 		salvando dados		
		produtoRepository.saveAll(produtos);
		userRepository.saveAll(users);
		pedidos.forEach(e -> pedidoService.save(e));
	}

}
