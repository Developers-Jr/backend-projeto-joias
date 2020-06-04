package com.pjoias.api.services;

import java.util.Optional;

import com.pjoias.api.models.entities.Historico;

public interface HistoricoService {
	
	Historico persistir(Historico historico);
	
	Optional<Historico> buscarPorIdVendedor(Long id);
	
	void deletarPorIdVendedor(Long id);
	
	void deletarPorId(Long id);
}
