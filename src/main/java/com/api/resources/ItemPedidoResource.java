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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.OrderItem;
import com.api.repository.ItemPedidoRepository;
import com.api.services.ItemPedidoService;

//@autor Jadson Feitosa #AE-36

@RestController
@RequestMapping("/api/intemPedido")
public class ItemPedidoResource implements ResourceBase<OrderItem, Long>{
	
	@Autowired
	private ItemPedidoService itemPedidoResource;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
//	Salvar itemPedido
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<OrderItem> save(@Valid OrderItem pEntity, HttpServletResponse response) {
		OrderItem itemPedidoSalvo = itemPedidoResource.save(pEntity);
		return ResponseEntity.ok(itemPedidoSalvo);
	}

//	Atualizar itemPedido
	@PutMapping
	public ResponseEntity<OrderItem> update(@Valid Long pID, OrderItem pEntity) {
		OrderItem itemPedidoSalvo = itemPedidoResource.update(pEntity);
		return ResponseEntity.ok(itemPedidoSalvo);
	}

//	Deletar itemPedido
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		itemPedidoRepository.deleteById(pID);
	}

//	Filtro por ID
	public ResponseEntity<OrderItem> findById(Long pID) {
		return  ResponseEntity.ok(itemPedidoRepository.findById(pID).get());
	}

//	Listar itemPedido
	@GetMapping
	public List<OrderItem> findAllList() {
		return itemPedidoRepository.findAll();
	}

	public Page<OrderItem> findAllPage(OrderItem pFilter, Pageable pPage) {
		return null;
	}
	
}
