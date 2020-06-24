package com.pjoias.api.services;

import java.util.List;

import com.pjoias.api.models.entities.MaletaHistorico;

public interface MaletaHistoricoService {
	MaletaHistorico persistir(MaletaHistorico maletaHistorico);
	
	List<MaletaHistorico> buscarPorIdVendedor(Long idVendedor);
	
	void deletarPorIdHistorico(Long id);
}
