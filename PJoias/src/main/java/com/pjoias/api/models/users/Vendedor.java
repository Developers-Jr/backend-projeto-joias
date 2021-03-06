package com.pjoias.api.models.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.pjoias.api.dtos.VendedorDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "vendedor")
public class Vendedor extends User{
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "id_admin")
	private Long idAdmin;
	
	public Vendedor() {}
	
	public Vendedor(VendedorDTO dto) {
		super(dto.getNome(), dto.getSobrenome(), dto.getEmail());
		this.telefone = dto.getTelefone();
		this.idAdmin = dto.getIdAdmin();
	}
}
