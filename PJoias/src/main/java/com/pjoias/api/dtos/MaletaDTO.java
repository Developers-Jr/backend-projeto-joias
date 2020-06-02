package com.pjoias.api.dtos;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.pjoias.api.models.entities.Maleta;
import com.pjoias.api.models.entities.Produto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MaletaDTO {
	private Long id;
	
	@NotNull(message = "O nome da maleta não pode estar vazio")
	private String nome;
	
	private double valor;
	
	private boolean fechada;
	
	private List<Produto> produtos;
	
	private Long id_admin;
	
	public MaletaDTO() {}
	
	public MaletaDTO(Maleta maleta) {
		this.id = maleta.getId();
		this.nome = maleta.getNome();
		this.valor = maleta.getValor();
		this.fechada = maleta.isFechada();
		this.produtos = maleta.getProdutos();
		this.id_admin = maleta.getId_admin();
	}
}
