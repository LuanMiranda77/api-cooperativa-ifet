package com.api.services;



import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Client;
import com.api.domain.UserAplication;
import com.api.repository.ClienteRepository;

//@autor Jadson Feitosa #AE-40

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
//	@Autowired
//	private UsuarioService usuarioService;
	
	
	public Client save(Client pEntity) {
//		if(pEntity.getUsuario().getId() == 0) {
//			pEntity.getUsuario().setId(null);
//			String senha = pEntity.getUsuario().getPassword();
//			long id = usuarioService.save(pEntity.getUsuario()).getId();
//			pEntity.getUsuario().setId(id);
//			pEntity.getUsuario().setPassword(senha);
//		}
		return clienteRepository.save(pEntity);
	}
	
	public Client update(Client pEntity) {
		Client clienteSalvo = clienteRepository.findById(pEntity.getId()).get();
		
		BeanUtils.copyProperties(pEntity, clienteSalvo, "id");
		clienteRepository.save(clienteSalvo);
		clienteSalvo.setId(pEntity.getId());
		return clienteSalvo;
		
	}
	
//	public Client getByUser(Long id) {
//		UserAplication user = usuarioService.findById(id);
//		return clienteRepository.findByUsuario(user);
//	}
//	
	public void isAtive(Client pEntity) {
		update(pEntity);
	}
	
//	public void salvaEndereços(List<Endereco> enderecos) {
//		
//	}
		
}
