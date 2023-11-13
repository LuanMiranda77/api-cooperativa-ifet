package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.OrderItem;

@Repository
public interface ItemPedidoRepository extends JpaRepository<OrderItem, Long>{

}
