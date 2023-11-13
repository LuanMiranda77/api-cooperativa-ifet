package com.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.domain.Client;

//@autor

@Repository
public interface ClienteRepository extends JpaRepository<Client, Long>{
	
//	public Client findByUsuario(UserAplication user);
	
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("Update Cliente cliente SET cliente.tipo =:tipo where cliente.id =:id")
//	public void updateStatus(@Param("id") Long id, @Param("tipo") TipoCliente tipo);

}
