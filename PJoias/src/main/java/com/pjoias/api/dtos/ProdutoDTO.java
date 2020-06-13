package com.pjoias.api.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.pjoias.api.models.entities.Produto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {
	
	private Long id;
	
	@NotNull(message = "O produto deve ter um nome")
	@NotBlank(message = "O nome do produto não pode estar em branco")
	private String nome;
	
	@NotNull(message = "O produto deve ter um valor")
	private double valor;
	
	@NotNull(message = "O produto estar em uma maleta")
	private Long idMaleta;
	
	public ProdutoDTO() {}
	
	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.idMaleta = produto.getIdMaleta();
	}
}
