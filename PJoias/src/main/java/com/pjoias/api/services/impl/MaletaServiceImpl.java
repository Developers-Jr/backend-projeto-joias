package com.pjoias.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjoias.api.models.entities.Maleta;
import com.pjoias.api.repositories.MaletaRepository;
import com.pjoias.api.services.MaletaService;

@Service
public class MaletaServiceImpl implements MaletaService {
	
	@Autowired
	private MaletaRepository maletaRepository;

	@Override
	public Maleta persist(Maleta maleta) {
		return maletaRepository.save(maleta);
	}

	@Override
	public Optional<Maleta> buscarPorId(Long id) {
		return maletaRepository.findById(id);
	}

	@Override
	public List<Maleta> buscarTodos() {
		return maletaRepository.findAll();
	}

	@Override
	public Optional<Maleta> buscarPorNome(String nome) {
		return maletaRepository.findByNome(nome);
	}

	@Override
	public void deletarPorId(Long id) {
		maletaRepository.deleteById(id);
	}

}
