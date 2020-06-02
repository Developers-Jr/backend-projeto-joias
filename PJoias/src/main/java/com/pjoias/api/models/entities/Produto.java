package com.pjoias.api.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pjoias.api.dtos.ProdutoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "produto")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "valor")
	private double valor;
	
	@Column(name = "id_maleta")
	private Long idMaleta;
	
	public Produto() {}
	
	public Produto(ProdutoDTO produtoDto) {
		this.nome = produtoDto.getNome();
		this.valor = produtoDto.getValor();
		this.idMaleta = produtoDto.getIdMaleta();
	}

}
