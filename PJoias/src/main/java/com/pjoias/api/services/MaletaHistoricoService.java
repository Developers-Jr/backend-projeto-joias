package com.pjoias.api.services;

import com.pjoias.api.models.entities.MaletaHistorico;

public interface MaletaHistoricoService {
	MaletaHistorico persistir(MaletaHistorico maletaHistorico);
	
	void deletarPorIdHistorico(Long id);
}
