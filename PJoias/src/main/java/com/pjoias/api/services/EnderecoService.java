package com.pjoias.api.services;

import java.util.Optional;

import com.pjoias.api.models.entities.Endereco;

public interface EnderecoService {
	Endereco persistir(Endereco endereco);
	
	Optional<Endereco> buscarPor(Long idRevendedor);
	
	void excluirPor(Long idEndereco);
}
