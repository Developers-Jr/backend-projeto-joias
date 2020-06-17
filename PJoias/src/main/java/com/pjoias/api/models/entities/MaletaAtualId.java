package com.pjoias.api.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class MaletaAtualId implements Serializable {
	
	public MaletaAtualId() {}
	
	public MaletaAtualId(Long idVendedor, Long idMaleta) {
		this.idVendedor = idVendedor;
		this.idMaleta = idMaleta;
	}

	private static final long serialVersionUID = 1L;

	@Column(name = "id_vendedor")
	private Long idVendedor;
	
	@Column(name = "id_maleta")
	private Long idMaleta;
}
