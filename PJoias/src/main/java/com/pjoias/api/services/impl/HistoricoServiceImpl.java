package com.pjoias.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjoias.api.models.entities.Historico;
import com.pjoias.api.repositories.HistoricoRepository;
import com.pjoias.api.services.HistoricoService;

@Service
public class HistoricoServiceImpl implements HistoricoService {
	
	@Autowired
	private HistoricoRepository historicoRepository;

	@Override
	public Historico persistir(Historico historico) {
		return historicoRepository.save(historico);
	}

	@Override
	public Optional<Historico> buscarPorIdVendedor(Long id) {
		return historicoRepository.findByIdVendedor(id);
	}

	@Override
	public void deletarPorIdVendedor(Long id) {
		historicoRepository.deleteByIdVendedor(id);
	}

	@Override
	public void deletarPorId(Long id) {
		historicoRepository.deleteById(id);
	}

}
