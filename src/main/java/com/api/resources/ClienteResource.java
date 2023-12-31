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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.domain.Client;
import com.api.domain.UserAplication;
import com.api.domain.enuns.TipoCliente;
import com.api.repository.ClienteRepository;
import com.api.services.ClienteService;

//@autor Jadson Feitosa #AE-40

@RestController
@RequestMapping(value="api/cliente")
public class ClienteResource implements ResourceBase<Client, Long>{
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
//	Salvar Cliente 
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Client> save(@Valid Client pEntity, HttpServletResponse response) {
		Client clienteSalvo = clienteService.save(pEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
	}

//	Atualizar cliente
	@PutMapping
	public ResponseEntity<Client> update(@Valid Long pID, Client pEntity) {
		Client clienteSalvo = clienteService.update(pEntity);
		return ResponseEntity.ok(clienteSalvo );
	}
	
//	Atualizar status
	@PutMapping("/tipo/{id}/{tipo}")
	public ResponseEntity<UserAplication> updateStatus(@PathVariable Long id, @PathVariable TipoCliente tipo) {
//		clienteRepository.updateStatus(id,tipo);
		return ResponseEntity.ok(null);
	}

//	Deletar Cliente
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		clienteRepository.deleteById(pID);
		
	}

//	Listar clientes
	@GetMapping
	public List<Client> findAllList() {
		return clienteRepository.findAll();
	}

//	Filtro por ID
//	@GetMapping("/{pID}")
//	public ResponseEntity<Client> findById(@PathVariable Long pID) {
//		Client clienteSalvo = clienteService.getByUser(pID);
//		clienteSalvo.getUsuario().setPassword(null);
//		return ResponseEntity.ok(clienteSalvo);
//	}

	public Page<Client> findAllPage(Client pFilter, Pageable pPage) {
		return null;
	}

	@Override
	public ResponseEntity<Client> findById(Long pID) {
		// TODO Auto-generated method stub
		return null;
	}

}
