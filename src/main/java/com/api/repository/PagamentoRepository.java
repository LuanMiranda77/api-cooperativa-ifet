package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Payment;

@Repository
public interface PagamentoRepository extends JpaRepository<Payment, Long>{

}
