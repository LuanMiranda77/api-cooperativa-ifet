package com.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.domain.Setor;
import com.api.domain.enuns.StatusUsuario;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long>{

    @Query(value = "SELECT * FROM setor  where deleted=0", nativeQuery = true)
	public List<Setor> findList();

    @Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update Setor setor SET setor.deleted=1 where setor.id =:id")
	public void deleteItem(@Param("id") Long id);

}
