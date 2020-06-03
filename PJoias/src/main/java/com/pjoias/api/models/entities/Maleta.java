package com.pjoias.api.models.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pjoias.api.dtos.MaletaDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "maleta")
public class Maleta {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Transient
	private double valor;
	
	@Column(name = "fechada")
	private boolean fechada;
	
	@Column(name = "id_admin")
	private Long id_admin;
	
	@OneToMany
	@JoinColumn(name = "id_maleta")
	private List<Produto> produtos;
	
	public Maleta() {}
	
	public Maleta(MaletaDTO maletaDto) {
		this.nome = maletaDto.getNome();
		this.valor = maletaDto.getValor();
		this.fechada = maletaDto.isFechada();
	}
}
