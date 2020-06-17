package com.pjoias.api.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pjoias.api.models.entities.MaletaHistorico;

public interface MaletaHistoricoRepository extends JpaRepository<MaletaHistorico, Long>{
	
	@Transactional
	@Modifying
	@Query("DELETE FROM MaletaHistorico MH WHERE MH.idHistorico = :#{#idHistorico}")
	void deleteByIdHistorico(@Param("idHistorico") Long idHistorico);
}
