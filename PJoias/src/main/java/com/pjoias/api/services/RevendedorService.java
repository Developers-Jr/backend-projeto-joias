package com.pjoias.api.services;

import java.util.List;

import com.pjoias.api.models.entities.Revendedor;

public interface RevendedorService {
	Revendedor persistir(Revendedor revendedor);
	
	List<Revendedor> buscarTodosPor(Long idVendedor);
	
	void excluirRevendedorPorId(Long id);
}
