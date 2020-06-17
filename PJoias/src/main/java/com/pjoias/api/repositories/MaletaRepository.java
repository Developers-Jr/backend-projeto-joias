package com.pjoias.api.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pjoias.api.models.entities.Maleta;

public interface MaletaRepository extends JpaRepository<Maleta, Long>{
	Optional<Maleta> findByNome(String name);

	@Transactional
	@Modifying
	@Query("FROM Maleta M " +
			"INNER JOIN MaletaAtual MA ON MA.idMaletaAtual.idMaleta =  M.id " +
			"WHERE MA.idMaletaAtual.idVendedor = :#{#vendedorId}"
	)
	List<Maleta> findByIdVendedor(@Param("vendedorId") Long vendedorId);
}
