package com.pjoias.api.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "historico")
public class Historico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "id_vendedor")
	private Long idVendedor;
	
	public Historico() {}
	
	public Historico(Long idVendedor) {
		this.idVendedor = idVendedor;
	}
}
