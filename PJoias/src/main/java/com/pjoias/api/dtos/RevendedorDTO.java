package com.pjoias.api.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.pjoias.api.models.entities.Revendedor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RevendedorDTO {
	private Long id;
	
	@NotNull(message = "O nome não pode estar vazio")
	@Size(min = 4)
	private String nome;
	
	@NotNull(message = "O telefone não pode estar vazio")
	@Size(min = 10)
	private String telefone;
	
	@CPF(message = "Insira um cpf válido")
	private String cpf;
	
	@NotNull(message = "Insira o valor do RG")
	private String rg;
	
	public RevendedorDTO() {}
	
	public RevendedorDTO(Revendedor revendedor) {
		this.id = revendedor.getId();
		this.nome = revendedor.getNome();
		this.telefone = revendedor.getTelefone();
		this.cpf = revendedor.getCpf();
		this.rg = revendedor.getRg();
	}
}
