package com.api.services.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

import com.api.domain.Client;
import com.api.domain.OrderItem;
import com.api.domain.OrderSale;
import com.api.domain.enuns.OrderStatus;

@Service
public class PedidoQueryImpl {

	@PersistenceContext
	private EntityManager manager;

	public List<OrderSale> findPedidosByEstatus(Date dtIni, Date dtFin, OrderStatus estatusPedido) {

		List<OrderSale> pedidos = new ArrayList<>();
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<OrderSale> query = builder.createQuery(OrderSale.class);
		Root<OrderSale> root = query.from(OrderSale.class);

		query.select(root);
		query.where(builder.between(root.get("dataFechamento"), dtIni, dtFin));
		query.where(builder.equal(root.get("estatus"), estatusPedido));

		pedidos = manager.createQuery(query).getResultList();
		
		return pedidos;

	}

	public List<OrderSale> findPedidosByCliente(Date dtIni, Date dtFin, Client cliente) {

		List<OrderSale> pedidos = new ArrayList<>();
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<OrderSale> query = builder.createQuery(OrderSale.class);
		Root<OrderSale> root = query.from(OrderSale.class);

		query.select(root);
		query.where(builder.between(root.get("dataFechamento"), dtIni, dtFin));
		query.where(builder.equal(root.get("cliente"), cliente));

		pedidos = manager.createQuery(query).getResultList();
		
		pedidos.forEach(e -> { 
			
			CriteriaQuery<OrderItem> query1 = builder.createQuery(OrderItem.class);
			Root<OrderItem> root2 = query1.from(OrderItem.class);
			query1.select(root2);
			query1.where(builder.equal(root2.get("pedido"), e));
			List<OrderItem> itens = manager.createQuery(query1).getResultList();
		
			e.setProducts(itens);
		});

		return pedidos;

	}

	public List<OrderSale> findPedidosByClienteStatus(Date dtIni, Date dtFin, Client cliente, OrderStatus status) {

		List<OrderSale> pedidos = new ArrayList<>();
		CriteriaBuilder builder = manager.getCriteriaBuilder();

		CriteriaQuery<OrderSale> query = builder.createQuery(OrderSale.class);
		Root<OrderSale> root = query.from(OrderSale.class);

		query.select(root);
		query.where(builder.between(root.get("dataFechamento"), dtIni, dtFin));
		query.where(builder.equal(root.get("cliente"), cliente));
		query.where(builder.equal(root.get("estatus"), status));

		pedidos = manager.createQuery(query).getResultList();

		return pedidos;

	}

}
