package com.pjoias.api.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.pjoias.api.dtos.EnderecoDTO;
import com.pjoias.api.dtos.RevendedorDTO;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Entity
@Table(name = "revendedor")
public class Revendedor {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "rg")
	private String rg;
	
	@Column(name = "id_vendedor")
	private Long idVendedor;
	
	@Transient
	private EnderecoDTO endereco;
	
	public Revendedor() {}
	
	public Revendedor(RevendedorDTO revendedorDto) {
		this.id = revendedorDto.getId();
		this.nome = revendedorDto.getNome();
		this.telefone = revendedorDto.getTelefone();
		this.cpf = revendedorDto.getCpf();
		this.rg = revendedorDto.getRg();
		this.endereco = revendedorDto.getEndereco();
	}
}
