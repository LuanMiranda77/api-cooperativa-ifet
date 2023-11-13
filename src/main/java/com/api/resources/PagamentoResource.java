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

import com.api.domain.Payment;
import com.api.repository.PagamentoRepository;
import com.api.services.PagamentoService;

//@autor Jadson Feitosa #AE-36

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoResource implements ResourceBase<Payment, Long>{

	@Autowired
	private PagamentoService pagamentoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
//	Salvar Pagamento
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Payment> save(@Valid Payment pEntity, HttpServletResponse response) {
		Payment pagamentoSalvo = pagamentoService.save(pEntity);
		return ResponseEntity.ok(pagamentoSalvo);
	}

//	Atualizar Pagamento
	@PutMapping
	public ResponseEntity<Payment> update(@Valid Long pID, Payment pEntity) {
		Payment pagamentoSalvo = pagamentoService.update(pEntity);
		return ResponseEntity.ok(pagamentoSalvo);
	}

//	Deletar pagamento	
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		pagamentoRepository.deleteById(pID);
	}
	
//	Filtro por ID
	public ResponseEntity<Payment> findById(Long pID) {
		return ResponseEntity.ok(pagamentoRepository.findById(pID).get());
	}
	
//	Listar Pagamentos
	@GetMapping
	public List<Payment> findAllList() {
		return pagamentoRepository.findAll();
	}
	
	public Page<Payment> findAllPage(Payment pFilter, Pageable pPage) {
		return null;
	}
	

}
