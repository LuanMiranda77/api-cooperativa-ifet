package com.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.api.domain.UserAplication;
import com.api.domain.enuns.StatusUsuario;

//@autor Jadson Feitosa #AE-40

@Repository
public interface UsuarioRepository extends JpaRepository<UserAplication, Long> {

	public UserAplication findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("Update UserAplication user SET user.status =:status where user.id =:id")
	public void updateStatus(@Param("id") Long id, @Param("status") StatusUsuario status);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value="update UserAplication set status =:status where setor_id =:id" , nativeQuery = true)
	public void updateStatusBySetor(@Param("id") Long id, @Param("status") String status);
	
	@Query(value = "SELECT * FROM user_aplication  where setor_id=:setor", nativeQuery = true)
	public List<UserAplication> findBySetor(Long setor);
	
	@Query(value = "SELECT MAX(codigo) as codigo FROM UserAplication  where setor_id=:setor", nativeQuery = true)
	public Long findMaxCodigoBySetor(Long setor);
}
