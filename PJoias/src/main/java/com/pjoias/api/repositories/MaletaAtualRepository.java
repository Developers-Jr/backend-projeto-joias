package com.pjoias.api.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pjoias.api.models.entities.MaletaAtual;
import com.pjoias.api.models.entities.MaletaAtualId;

public interface MaletaAtualRepository extends JpaRepository<MaletaAtual, MaletaAtualId>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM MaletaAtual MA WHERE MA.idMaletaAtual.idVendedor = :#{#id}")
	void deleteByVendedorId(@Param("id") Long id);
	
	@Transactional
	@Query("FROM MaletaAtual MA WHERE MA.idMaletaAtual.idMaleta = :#{#id}")
	Optional<MaletaAtual> findByMaleta(@Param("id") Long id);
}
