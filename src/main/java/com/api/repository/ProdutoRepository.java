package com.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.domain.Categoria;
import com.api.domain.Product;
import com.api.domain.UserAplication;
import com.api.domain.enuns.OrderStatus;

//@autor Jadson Feitosa #29

@Repository
public interface ProdutoRepository extends JpaRepository<Product, Long> {
	
	public Product findByEan(String codigoBarras);
	
	public boolean existsByEan(String codigoBarras);
	
	public List<Product> findProdutoByNomeContains(String title);
	
	@Query(value = "SELECT * FROM produto where estabelecimento_id=:estabelecimento", nativeQuery = true)
	public List<Product> findByEstabelecimento(Long estabelecimento);
	
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("from Produto produto where produto.categoria.id =:id")
//	public List<Product> findProdutoByCategoria(@Param("id") Long id);
	
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("Update Produto produto SET produto.idMarketplace =:code where produto.id =:id")
//	public void updateIDMarket(@Param("id") Long id, @Param("code")String code );

}
