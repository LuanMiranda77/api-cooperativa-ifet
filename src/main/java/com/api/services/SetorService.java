package com.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Setor;
import com.api.repository.SetorRepository;
import com.api.repository.UsuarioRepository;

@Service
public class SetorService implements ServiceBase<Setor, Long> {
	
	@Autowired
	SetorRepository repository;
	
	@Autowired
	UsuarioRepository userRepository;
	
	@Override
	public Setor save(Setor pEntity) {
		Setor empresa = repository.save(pEntity); 
		return empresa;
	}
	

	@Override
	public Setor update(Long pID, Setor pEntity) {
		Setor empresa = repository.findById(pEntity.getId()).get();
		BeanUtils.copyProperties(pEntity, empresa, "id");
		this.save(empresa);
		empresa.setId(pEntity.getId());
		return empresa;
	}
	
	public void updateStatus(Long pID, String status) { 
//		repository.updateStatus(pID, status);
		if(status.equals("S")) {
			userRepository.updateStatusBySetor(pID, "S");
		}else {
			userRepository.updateStatusBySetor(pID, "B");
		}
	}
	

}
