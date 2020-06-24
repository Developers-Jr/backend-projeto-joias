package com.pjoias.api.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjoias.api.models.entities.MaletaHistorico;
import com.pjoias.api.repositories.MaletaHistoricoRepository;
import com.pjoias.api.services.MaletaHistoricoService;

@Service
public class MaletaHistoricoServiceImpl implements MaletaHistoricoService {
	
	@Autowired
	private MaletaHistoricoRepository maletaHistoricoRepository;

	@Override
	public void deletarPorIdHistorico(Long id) {
		maletaHistoricoRepository.deleteByIdHistorico(id);
	}


	@Override
	public MaletaHistorico persistir(MaletaHistorico maletaHistorico) {
		return maletaHistoricoRepository.save(maletaHistorico);
	}


	@Override
	public List<MaletaHistorico> buscarPorIdVendedor(Long idVendedor) {
		return maletaHistoricoRepository.findByIdVendedor(idVendedor);
	}

}
