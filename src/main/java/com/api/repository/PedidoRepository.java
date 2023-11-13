package com.api.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.domain.OrderSale;
import com.api.domain.enuns.OrderStatus;

@Repository
public interface PedidoRepository extends JpaRepository<OrderSale, Long>{
	
	public List<OrderSale> findByDateCreateBetween(Date dtIni, Date dtFin );
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update OrderSale orderSale SET orderSale.status =:newStatus where orderSale.id =:id")
	public void updateStatus(@Param("id") Long id, @Param("newStatus") OrderStatus newStatus);

}
