package com.pjoias.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pjoias.api.models.entities.Maleta;

public interface MaletaRepository extends JpaRepository<Maleta, Long>{
	Optional<Maleta> findByNome(String name);

	@Query("FROM Maleta M " +
			"INNER JOIN MaletaHistorico MH ON MH.maletaHistoricoId.idMaleta = M.id " +
			"INNER JOIN Historico H ON H.id = MH.maletaHistoricoId.idHistorico " +
			"WHERE H.idVendedor = :vendedorId"
	)
	List<Maleta> findByIdVendedor(@Param("vendedorId") Long vendedorId);
}
