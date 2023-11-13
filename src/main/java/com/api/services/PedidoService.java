package com.api.services;



import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Client;
import com.api.domain.OrderSale;
import com.api.domain.OrderItem;
import com.api.domain.enuns.OrderStatus;
import com.api.repository.ItemPedidoRepository;
import com.api.repository.PedidoRepository;
import com.api.services.filter.PedidoQueryImpl;

//@autor Jadson Feitosa #AE-36

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PedidoQueryImpl pedidoQueryImpl;
	
	public OrderSale save(OrderSale pEntity) {
		List<OrderItem>itemPedidos = pEntity.getProducts();
		OrderSale pedidoSalvo = pedidoRepository.save(pEntity);
		
		for (int i = 0; i < itemPedidos.size(); i++) {
			pEntity.getProducts().get(i).setOrder(pedidoSalvo);
			pEntity.getProducts().get(i).setDateSale(pedidoSalvo.getDateClose());
			itemPedidoRepository.save(pEntity.getProducts().get(i));
		}
		return pedidoRepository.save(pEntity);
	}
	
	public OrderSale update(OrderSale pEntity) {
		OrderSale pedidoSalvo = null;
		
		if(pEntity.getStatus().equals(OrderStatus.FINALIZADO)) {
			System.out.println("pagou");
//			if(pEntity.getPagamento().getEstatus().equals(EstatusPagamento.APROVADO)) {
//				pedidoSalvo = pedidoRepository.findById(pEntity.getId()).get();
//				BeanUtils.copyProperties(pEntity, pedidoSalvo, "id");
//				pedidoRepository.save(pedidoSalvo);
//			}
//			else {
//				throw new PagamentoNaoAprovadoException();
//			}
		}else {
			
			pedidoSalvo = pedidoRepository.findById(pEntity.getId()).get();
			BeanUtils.copyProperties(pEntity, pedidoSalvo, "id");
			pedidoRepository.save(pedidoSalvo);
			pedidoSalvo.setId(pEntity.getId());
		}
		return pedidoSalvo;
	}
	
	public void isAtive(OrderSale pEntity) {
		update(pEntity);
	}
	
	public  List<OrderSale> findPedidosByEstatus(Date dtIni, Date dtFin, OrderStatus estatusPedido){
		return pedidoQueryImpl.findPedidosByEstatus(dtIni, dtFin, estatusPedido);
	}
	
	public  List<OrderSale> findPedidosByCliente(Date dtIni, Date dtFin, Client cliente){
		return pedidoQueryImpl.findPedidosByCliente(dtIni, dtFin, cliente);
	}
	
	public  List<OrderSale> findPedidosByClienteStatus(Date dtIni, Date dtFin, Client cliente, OrderStatus status){
		return pedidoQueryImpl.findPedidosByClienteStatus(dtIni, dtFin, cliente, status);
	}
		
}
