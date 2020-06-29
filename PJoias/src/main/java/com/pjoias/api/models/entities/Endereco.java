package com.pjoias.api.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "endereco")
public class Endereco {
	
	public Endereco() {}
	
	public Endereco(String estado, String cidade, String rua, String numero, String cep, Long idRevendedor) {
		this.estado = estado;
		this.cidade = cidade;
		this.rua = rua;
		this.numero = numero;
		this.cep = cep;
		this.idRevendedor = idRevendedor;
	}
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "cod_endereco")
	private Long id;
	
	@Column(name = "estado")
	private String estado;
	
	@Column(name = "cidade")
	private String cidade;
	
	@Column(name = "rua")
	private String rua;
	
	@Column(name = "numero")
	private String numero;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "id_revendedor")
	private Long idRevendedor;
}
