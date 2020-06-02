package com.pjoias.api.services;

import com.pjoias.api.models.entities.Produto;

public interface ProdutoService {
	
	Produto persistir(Produto produto);
	
	void excluirPorId(Long id);

}
