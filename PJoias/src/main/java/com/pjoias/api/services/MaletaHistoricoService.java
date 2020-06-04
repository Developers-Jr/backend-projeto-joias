package com.pjoias.api.services;

import com.pjoias.api.models.entities.MaletaHistorico;
import com.pjoias.api.models.entities.MaletaHistoricoId;

public interface MaletaHistoricoService {
	MaletaHistorico persistir(MaletaHistorico maletaHistorico);
	
	void deletar(MaletaHistoricoId maletaHistoricoId);
	
	void deletarPorIdHistorico(Long id);
}
