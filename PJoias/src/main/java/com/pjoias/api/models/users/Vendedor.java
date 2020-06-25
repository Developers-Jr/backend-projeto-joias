package com.pjoias.api.models.users;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.pjoias.api.dtos.VendedorDTO;
import com.pjoias.api.models.entities.Revendedor;

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
	
	@OneToMany
	@JoinColumn(name = "id_vendedor")
	@Cascade(CascadeType.ALL)
	private List<Revendedor> revendedor;
	
	public Vendedor() {}
	
	public Vendedor(VendedorDTO dto) {
		super(dto.getNome(), dto.getSobrenome(), dto.getEmail());
		this.telefone = dto.getTelefone();
		this.idAdmin = dto.getIdAdmin();
	}
}
