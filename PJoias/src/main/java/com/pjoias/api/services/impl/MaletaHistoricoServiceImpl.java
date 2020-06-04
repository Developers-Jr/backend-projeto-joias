package com.pjoias.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjoias.api.models.entities.MaletaHistorico;
import com.pjoias.api.models.entities.MaletaHistoricoId;
import com.pjoias.api.repositories.MaletaHistoricoRepository;
import com.pjoias.api.services.MaletaHistoricoService;

@Service
public class MaletaHistoricoServiceImpl implements MaletaHistoricoService {
	
	@Autowired
	private MaletaHistoricoRepository maletaHistoricoRepository;

	@Override
	public MaletaHistorico persistir(MaletaHistorico maletaHistorico) {
		return maletaHistoricoRepository.save(maletaHistorico);
	}

	@Override
	public void deletar(MaletaHistoricoId maletaHistoricoId) {
		maletaHistoricoRepository.deleteById(maletaHistoricoId);
	}

	@Override
	public void deletarPorIdHistorico(Long id) {
		maletaHistoricoRepository.deleteByIdHistorico(id);
	}

}
