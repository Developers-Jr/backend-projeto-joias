package com.pjoias.api.services;

import com.pjoias.api.models.entities.Endereco;

public interface EnderecoService {
	Endereco persistir(Endereco endereco);
	
	void excluirPor(Long idRevendedor);
}
