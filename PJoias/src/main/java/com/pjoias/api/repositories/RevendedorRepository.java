package com.pjoias.api.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pjoias.api.models.entities.Revendedor;

public interface RevendedorRepository extends JpaRepository<Revendedor, Long>{
	
	@Transactional
	@Query("FROM Revendedor R WHERE R.idVendedor = :#{#idVendedor}")
	List<Revendedor> findByVendedorId(@Param("idVendedor") Long idVendedor);
}
