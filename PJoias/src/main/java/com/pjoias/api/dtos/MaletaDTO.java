package com.pjoias.api.dtos;

import javax.validation.constraints.NotNull;

import com.pjoias.api.models.entities.Maleta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaletaDTO {
	private Long id;
	
	@NotNull
	private String nome;
	
	private double valor;
	
	@NotNull
	private boolean status_maleta;
	
	private Long id_admin;
	
	public MaletaDTO() {}
	
	public MaletaDTO(Maleta maleta) {
		this.id = maleta.getId();
		this.nome = maleta.getNome();
		this.valor = maleta.getValor();
		this.status_maleta = maleta.isStatus_maleta();
		this.id_admin = maleta.getId_admin();
	}
}
