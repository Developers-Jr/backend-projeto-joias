package com.pjoias.api.services;

import java.util.List;
import java.util.Optional;

import com.pjoias.api.models.entities.Revendedor;

public interface RevendedorService {
	Revendedor persistir(Revendedor revendedor);
	
	List<Revendedor> buscarTodosPor(Long idVendedor);
	
	Optional<Revendedor> buscarPor(Long idRevendedor);
	
	void excluirRevendedorPorId(Long id);
}
