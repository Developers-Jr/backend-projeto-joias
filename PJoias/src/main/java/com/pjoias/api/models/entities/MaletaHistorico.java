package com.pjoias.api.models.entities;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "maleta_historico")
public class MaletaHistorico {
	
	@EmbeddedId
	private MaletaHistoricoId maletaHistoricoId;
	
	@Column(name = "valor")
	private double valor;
	
	public MaletaHistorico() {}
	
	public MaletaHistorico(MaletaHistoricoId id, List<Produto> produtos) {
		this.maletaHistoricoId = id;
		this.valor = this.converterProdutoValor(produtos);
	}
	
	private double converterProdutoValor(List<Produto> produtos) {
		double valor = 0.0;
		List<Double> valores = produtos.stream().map(p -> p.getValor()).collect(Collectors.toList());
		
		for(double val : valores) {
			valor += val;
		}
		
		return valor;
	}
}
