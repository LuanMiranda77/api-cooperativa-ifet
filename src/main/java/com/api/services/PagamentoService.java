package com.api.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Payment;
import com.api.repository.PagamentoRepository;

//@autor Jadson Feitosa #AE-36

@Service
public class PagamentoService {
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public Payment save(Payment pEntity) {
		return pagamentoRepository.save(pEntity);
	}
	
	public Payment update(Payment pEntity) {
		Payment pagamentoSalvo = pagamentoRepository.findById(pEntity.getId()).get();
		
		BeanUtils.copyProperties(pEntity, pagamentoSalvo, "id");
		pagamentoRepository.save(pagamentoSalvo);
		pagamentoSalvo.setId(pEntity.getId());
		return pagamentoSalvo;
		
	}
	
	public void isAtive(Payment pEntity) {
		update(pEntity);
	}

}
