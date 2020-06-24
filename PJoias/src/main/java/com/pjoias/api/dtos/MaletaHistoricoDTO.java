package com.pjoias.api.dtos;

import com.pjoias.api.models.entities.MaletaHistorico;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaletaHistoricoDTO {
	private Long id;
	
	private Long idMaleta;
	
	private double valor;
	
	public MaletaHistoricoDTO(MaletaHistorico maletaHistorico) {
		this.id = maletaHistorico.getId();
		this.idMaleta = maletaHistorico.getIdMaleta();
		this.valor = maletaHistorico.getValor();
	}
}
