package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long>{

}
