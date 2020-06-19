package com.pjoias.api.models.entities;

import static com.pjoias.api.utils.ConvertProductsInValues.converterProdutoValor;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "maleta_atual_vendedor")
public class MaletaAtual {
	
	public MaletaAtual() {}
	
	public MaletaAtual(MaletaAtualId id, List<Produto> produtos) {
		this.valorRestante = converterProdutoValor(produtos);
		this.idMaletaAtual = id;
	}

	@EmbeddedId
	private MaletaAtualId idMaletaAtual;
	
	@Column(name = "valor_restante")
	private double valorRestante;
	
	@Column(name = "valor_vendido")
	private double valorVendido;
	
	@Column(name = "fechada")
	private boolean fechada;
}
