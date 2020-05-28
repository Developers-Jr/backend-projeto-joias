package com.pjoias.api.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.pjoias.api.models.users.Vendedor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendedorDTO {
	private Long id;
	
	@NotNull
	@Size(min = 4)
	private String nome;
	
	@NotNull
	@Size(min = 4)
	private String sobrenome;
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String senha;
	
	@NotNull
	private String telefone;
	
	private Long idAdmin;
	
	public VendedorDTO() {}
	
	public VendedorDTO(Vendedor vendedor) {
		this.id = vendedor.getId();
		this.email = vendedor.getEmail();
		this.nome = vendedor.getNome();
		this.sobrenome = vendedor.getSobrenome();
		this.telefone = vendedor.getTelefone();
		this.idAdmin = vendedor.getIdAdmin();
	}
}
