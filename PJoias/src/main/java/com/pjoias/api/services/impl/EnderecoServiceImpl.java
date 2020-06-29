package com.pjoias.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjoias.api.models.entities.Endereco;
import com.pjoias.api.repositories.EnderecoRepository;
import com.pjoias.api.services.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	@Override
	public Endereco persistir(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}

	@Override
	public void excluirPor(Long idEndereco) {
		enderecoRepository.deleteById(idEndereco);
	}

	@Override
	public Optional<Endereco> buscarPor(Long idRevendedor) {
		return enderecoRepository.findByIdRevendedor(idRevendedor);
	}

}
