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
	
	@NotNull(message = "O nome não pode estar vazio")
	@Size(min = 4)
	private String nome;
	
	@NotNull(message = "O sobrenome não pode estar vazio")
	@Size(min = 4)
	private String sobrenome;
	
	@NotNull
	@Email(message = "Este não é um e-mail válido")
	private String email;
	
	private String senha;
	
	@NotNull(message = "O telefone não pode estar vazio")
	private String telefone;
	
	private double valorDeMaletasTotal;
	
	private Long idAdmin;
	
	public VendedorDTO() {}
	
	public VendedorDTO(Vendedor vendedor, double valorDeMaletasTotal) {
		this.id = vendedor.getId();
		this.email = vendedor.getEmail();
		this.nome = vendedor.getNome();
		this.sobrenome = vendedor.getSobrenome();
		this.telefone = vendedor.getTelefone();
		this.idAdmin = vendedor.getIdAdmin();
		this.valorDeMaletasTotal = valorDeMaletasTotal;
	}
}
