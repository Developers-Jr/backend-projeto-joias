package com.pjoias.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjoias.api.models.entities.Revendedor;
import com.pjoias.api.repositories.RevendedorRepository;
import com.pjoias.api.services.RevendedorService;

@Service
public class RevendedorServiceImpl implements RevendedorService {
	
	@Autowired
	private RevendedorRepository revendedorRepository;

	@Override
	public Revendedor persistir(Revendedor revendedor) {
		return revendedorRepository.save(revendedor);
	}

	@Override
	public void excluirRevendedorPorId(Long id) {
		revendedorRepository.deleteById(id);
	}

	@Override
	public List<Revendedor> buscarTodosPor(Long idVendedor) {
		return revendedorRepository.findByVendedorId(idVendedor);
	}

	@Override
	public Optional<Revendedor> buscarPor(Long idRevendedor) {
		return revendedorRepository.findById(idRevendedor);
	}

}
