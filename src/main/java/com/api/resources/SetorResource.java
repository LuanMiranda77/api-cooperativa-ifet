package com.api.resources;

import java.awt.print.Pageable;
import java.util.ArrayList;
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

import com.api.domain.Setor;
import com.api.domain.enuns.TipoUsuario;
import com.api.repository.SetorRepository;
import com.api.services.SetorService;

@RestController
@RequestMapping("/api/estabelecimento")
public class SetorResource implements ResourceBase<Setor, Long> {
	
	@Autowired
	private SetorRepository estabelecimentoRepository;
	
	@Autowired
	private SetorService estabelecimentoService;
	

	//	Salvar estabelecimento
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Setor> save(@Valid @RequestBody Setor pEntity, HttpServletResponse response) {
		Setor EmpresaSalvo = null;
		EmpresaSalvo = estabelecimentoService.save(pEntity);
		return ResponseEntity.status(HttpStatus.CREATED).body(EmpresaSalvo);
	}

	@Override
	public ResponseEntity<Setor> update(@Valid Long pID, Setor pEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		// TODO Auto-generated method stub
		
	}

	public ResponseEntity<Setor> findById(Long pID) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(estabelecimentoRepository.findById(pID).get());
	}

	@Override
	public Page<Setor> findAllPage(Setor pFilter, Pageable pPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@GetMapping("/estabelecimentos/{estabelecimento}/{tipo}")
	public List<Setor> findAllList(@PathVariable Long estabelecimento, @PathVariable String tipo) {
		if(tipo.equals(TipoUsuario.MASTER.getDescricao())) {
			return estabelecimentoRepository.findAll();
		}else {
			List<Setor> lista = new ArrayList<>();
			Setor estabelecimentoSalvo  = estabelecimentoRepository.getById(estabelecimento);
			lista.add(estabelecimentoSalvo);
			return lista;
		}
	}

	@GetMapping
	public List<Setor> findAllList() {
		return estabelecimentoRepository.findList();
	}
	
	@PutMapping("/delete/{id}")
	public ResponseEntity<Setor> setStatus(@PathVariable Long id) {
		estabelecimentoRepository.deleteItem(id);
		return ResponseEntity.ok(null);
	}

}
