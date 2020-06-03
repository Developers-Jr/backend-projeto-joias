package com.pjoias.api.services;

import java.util.Optional;

import com.pjoias.api.models.entities.Produto;

public interface ProdutoService {
	
	Produto persistir(Produto produto);
	
	Optional<Produto> buscarPorId(Long id);
	
	void excluirPorId(Long id);

}
