package com.pjoias.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjoias.api.models.entities.MaletaAtual;
import com.pjoias.api.models.entities.MaletaAtualId;
import com.pjoias.api.repositories.MaletaAtualRepository;
import com.pjoias.api.services.MaletaAtualService;

@Service
public class MaletaAtualServiceImpl implements MaletaAtualService {
	
	@Autowired
	private MaletaAtualRepository maletaAtualRepository;

	@Override
	public MaletaAtual persistir(MaletaAtual maletaAtual) {
		return maletaAtualRepository.save(maletaAtual);
	}

	@Override
	public void deletar(MaletaAtualId id) {
		maletaAtualRepository.deleteById(id);
	}

	@Override
	public void deletarPorIdVendedor(Long id) {
		maletaAtualRepository.deleteByVendedorId(id);
	}

	@Override
	public Optional<MaletaAtual> buscarPorId(MaletaAtualId id) {
		return maletaAtualRepository.findById(id);
	}

	@Override
	public Optional<MaletaAtual> buscarPorMaleta(Long id) {
		return maletaAtualRepository.findByMaleta(id);
	}
}
