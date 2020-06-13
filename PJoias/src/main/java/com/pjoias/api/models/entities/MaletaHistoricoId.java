package com.pjoias.api.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class MaletaHistoricoId implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "id_historico")
	private Long idHistorico;
	
	@Column(name = "id_maleta")
	private Long idMaleta;
	
	public MaletaHistoricoId() {}
	
	public MaletaHistoricoId(Long idHistorico, Long idMaleta) {
		this.idHistorico = idHistorico;
		this.idMaleta = idMaleta;
	}
}
