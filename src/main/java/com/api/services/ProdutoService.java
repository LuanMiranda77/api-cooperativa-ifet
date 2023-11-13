package com.api.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.Product;
import com.api.repository.ProdutoRepository;
import com.api.services.exceptions.ItemExistException;
import com.api.services.filter.ProdutoQueryImpl;

// @autor Jadson Feitosa #29

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoQueryImpl produtoQueryImpl;

	public Product save(Product pEntity){
		if(produtoRepository.existsByEan(pEntity.getEan())) {
			throw new ItemExistException();
		}
		Product produtoSalvo = produtoRepository.save(pEntity);
		return produtoSalvo;
	}

	public Product update(Long pID, Product pEntity) {
		Product produtoSalvo = produtoRepository.findById(pID).get();
		
		BeanUtils.copyProperties(pEntity, produtoSalvo,"id");
		produtoRepository.save(produtoSalvo); 
		produtoSalvo.setId(pEntity.getId());
		return produtoSalvo;
	}
	
	public void deleteAll(List<Product> pList) {
		produtoRepository.deleteAll(pList);
		
	}
	
	public List<Product> findFilterProdutos(String tipoFilter, String dados){
		return produtoQueryImpl.findFilterProduto(tipoFilter, dados);
	}
	
		
}
