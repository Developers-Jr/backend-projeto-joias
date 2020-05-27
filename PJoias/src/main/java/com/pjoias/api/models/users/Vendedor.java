package com.pjoias.api.models.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pjoias.api.dtos.VendedorDTO;

import lombok.Data;


@Data
@Entity
@Table(name = "vendedor")
public class Vendedor extends User{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "id_admin")
	private Long idAdmin;
	
	public Vendedor() {}
	
	public Vendedor(VendedorDTO dto) {
		super(dto.getNome(), dto.getSobrenome(), dto.getEmail(), dto.getSenha());
		this.telefone = dto.getTelefone();
		this.idAdmin = dto.getIdAdmin();
	}
}
