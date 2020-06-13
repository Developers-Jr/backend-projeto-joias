package com.pjoias.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pjoias.api.models.entities.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Long>{
	Optional<Historico> findByIdVendedor(Long id);
	
	void deleteByIdVendedor(Long id);
}
