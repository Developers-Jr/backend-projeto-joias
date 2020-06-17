package com.pjoias.api.services;

import java.util.Optional;

import com.pjoias.api.models.entities.MaletaAtual;
import com.pjoias.api.models.entities.MaletaAtualId;

public interface MaletaAtualService {
	MaletaAtual persistir(MaletaAtual maletaAtual);
	
	void deletar(MaletaAtualId id);
	
	void deletarPorIdVendedor(Long id);
	
	Optional<MaletaAtual> buscarPorId(MaletaAtualId id);
}
