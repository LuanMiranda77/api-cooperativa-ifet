package com.api.resources;

import java.awt.print.Pageable;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.OrderSale;
import com.api.domain.TO.DashboardTO;
import com.api.domain.enuns.OrderStatus;
import com.api.repository.PedidoRepository;
import com.api.services.DashboardService;
import com.api.services.PedidoService;
import com.api.services.filter.Dashboard;

//@autor Jadson Feitosa #AE-36

@RestController
@RequestMapping("/api/pedido")
public class PedidoResource implements ResourceBase<OrderSale, Long>{

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private DashboardService dashboardService;
	

//	Salvar Pedido
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<OrderSale> save(@Valid OrderSale pEntity, HttpServletResponse response) {
		OrderSale pedidoSalvo = pedidoService.save(pEntity);
		return ResponseEntity.ok(pedidoSalvo);
	}

//	Atualizar pedido
	@PutMapping
	public ResponseEntity<OrderSale> update(@Valid Long pID, @Valid OrderSale pEntity) {
		OrderSale pedidoSalvo = pedidoService.update(pEntity);
		return ResponseEntity.ok(pedidoSalvo);
	}
	
//	Atualizar pedido
	@PutMapping("/status/{id}/{status}")
	public ResponseEntity<OrderSale> updateStatus(@PathVariable Long id, @PathVariable OrderStatus status) {
		pedidoRepository.updateStatus(id,status);
		return ResponseEntity.ok(null);
	}

//	Deletar Pedido
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		pedidoRepository.deleteById(pID);
	}

//	Filtro por ID
	@GetMapping("/{pID}")
	public ResponseEntity<OrderSale> findById(@PathVariable Long pID) {
		return  ResponseEntity.ok(pedidoRepository.findById(pID).get());
	}


//	Listar Pedido
	@GetMapping
	public List<OrderSale> findAllList() {
		return pedidoRepository.findAll();
	}
	

//	Listar Pedido
	@PostMapping("/find-data")
	public List<OrderSale> findByPedidoEstatus(@RequestBody  OrderSale pedido ) {
		return pedidoService.findPedidosByEstatus(pedido.getDateCreate(), pedido.getDateClose(), pedido.getStatus());
	}
	
	//	Listar Pedido
	@PostMapping("/find-data-all")
	public List<OrderSale> findByPedidoData(@RequestBody  OrderSale pedido ) {
		return pedidoRepository.findByDateCreateBetween(pedido.getDateCreate(), pedido.getDateClose());
	}
	
//	Listar Pedido
//	@PostMapping("/find-pedidos-by-cliente")
//	public List<Order> findPedidoByCliente(@RequestBody Order pedido ) {
//		return pedidoService.findPedidosByCliente(pedido.getDateCreate(), pedido.getDateClose(), pedido.getCliente());
//	}
	
//	Listar Pedido
	@PostMapping("/find-pedidos-by-cliente-status")
//	public List<Order> findPedidoByClienteStatus(@RequestBody Order pedido ) {
//		return pedidoService.findPedidosByClienteStatus(pedido.getDateCreate(), pedido.getDateClose(), pedido.getCliente(), pedido.getEstatus());
//	}



	public Page<OrderSale> findAllPage(OrderSale pFilter, Pageable pPage) {
		return null;
	}
	
	@PostMapping("/dashboard")
	public Dashboard findDashboard(@RequestBody DashboardTO dashboard) {
		return dashboardService.findDesthboard(dashboard.getDataDeCriacao(), dashboard.getDataFechamento());
	}
	
	
	
	
}
