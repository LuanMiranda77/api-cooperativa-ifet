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

import com.api.domain.Setor;
import com.api.domain.UserAplication;
import com.api.domain.TO.UserLoginTO;
import com.api.domain.TO.UserTO;
import com.api.domain.enuns.StatusUsuario;
import com.api.repository.SetorRepository;
import com.api.repository.UsuarioRepository;
import com.api.resources.exception.LoginException;
import com.api.services.UsuarioService;

//@autor Jadson Feitosa #AE-42

@RestController
@RequestMapping("/api/usuario")
public class UsuarioResource implements ResourceBase<UserAplication, Long> {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	@Autowired 
	private SetorRepository estabelecimentoRepository;
	
	@PostMapping("/login")
	public ResponseEntity<UserAplication> login(@RequestBody UserLoginTO pEntity, HttpServletResponse response){
		UserAplication userSalvo = usuarioRepository.findByEmail(pEntity.getEmail());
		
		if(userSalvo != null) {
			if(userSalvo.getEmail().equals(pEntity.getEmail()) 
					&& userSalvo.getPassword().equals(pEntity.getPassword())) {
				userSalvo.setPassword("");
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(userSalvo);
				
			}else {
				throw new LoginException();
			}
		}else {
			throw new LoginException();
		}
	}
	
	
//	Salvar Usuario
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UserAplication> save(@Valid @RequestBody UserTO pEntity, HttpServletResponse response) {
		Setor setor = estabelecimentoRepository.getById(pEntity.getEstabelecimento());
		UserAplication user = pEntity.converteParaEntidadeSemEstabelecimento(pEntity);
		user.setSetor(setor);
		UserAplication usuarioSalvo = usuarioService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}
	
//	recuperar senha 
//	--ADD --Luan Miranda - AE-10---------------------------------------------
	@PostMapping("/recuperasenha")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Boolean> recuperaSenha(@RequestBody UserLoginTO pEntity, HttpServletResponse response) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioService.recuperaSenha(pEntity.getEmail()));
	}
	
	@PostMapping("/email-marketing")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Boolean> enviarEmailMassa(@RequestBody List<String> emails, HttpServletResponse response) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(usuarioService.enviarEmailMarketing(emails));
	}
//----------------------------------fim--------------------------------------------	
	
	
//	Atualizar Usuario
	@PutMapping
	public ResponseEntity<UserAplication> update(@Valid @RequestBody UserTO pEntity) {
		Setor setor = estabelecimentoRepository.getById(pEntity.getEstabelecimento());
		UserAplication user = pEntity.converteParaEntidadeSemEstabelecimento(pEntity);
		user.setSetor(setor);
		UserAplication usuarioSalvo = usuarioService.update(user);
		return ResponseEntity.ok(usuarioSalvo);
	}
	
//	Atualizar status
	@PutMapping("/status/{id}/{status}")
	public ResponseEntity<UserAplication> updateStatus(@PathVariable Long id, @PathVariable StatusUsuario status) {
		usuarioRepository.updateStatus(id,status);
		return ResponseEntity.ok(null);
	}

//	Deletar Usuario
	@DeleteMapping("/{pID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(Long pID) {
		usuarioRepository.deleteById(pID);
	}

//	Filtro por ID
	@GetMapping("/{pID}")
	public ResponseEntity<UserAplication> findById(@PathVariable Long pID) {
		UserAplication usuarioSalvo = usuarioRepository.findById(pID).get();
		usuarioSalvo.setPassword(null);
		return ResponseEntity.ok(usuarioSalvo);
	}

//  Listar usuario
	@GetMapping
	public List<UserAplication> findAllList() {
		return usuarioRepository.findAll();
	}

	@GetMapping("/estabelecimento/{pID}")
	public List<UserAplication> findByEstabelecimento(@PathVariable Long pID) {
		return usuarioRepository.findBySetor(pID);
	}
	
	@Override
	public Page<UserAplication> findAllPage(UserAplication pFilter, Pageable pPage) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ResponseEntity<UserAplication> save(@Valid UserAplication pEntity, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ResponseEntity<UserAplication> update(@Valid Long pID, UserAplication pEntity) {
		// TODO Auto-generated method stub
		return null;
	}

}
