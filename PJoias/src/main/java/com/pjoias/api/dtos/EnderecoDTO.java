package com.pjoias.api.dtos;

import javax.validation.constraints.NotNull;

import com.pjoias.api.models.entities.Endereco;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDTO {
	public EnderecoDTO() {}
	
	public EnderecoDTO(Endereco endereco) {
		this.cidade = endereco.getCidade();
		this.estado = endereco.getEstado();
		this.cep = endereco.getCep();
		this.rua = endereco.getRua();
		this.numero = endereco.getNumero();
	}
	
	@NotNull(message = "O estado não pode estar vazio")
	private String estado;
	
	@NotNull(message = "A cidade não pode estar vazia")
	private String cidade;
	
	@NotNull(message = "A rua não pode estar vazia")
	private String rua;
	
	@NotNull(message = "O numero não pode estar vazio")
	private String numero;
	
	@NotNull(message = "O CEP não pode estar vazio")
	private String cep;
}
