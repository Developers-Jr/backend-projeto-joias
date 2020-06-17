package com.pjoias.api.models.entities;

import static com.pjoias.api.utils.ConvertProductsInValues.converterProdutoValor;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "maleta_historico")
public class MaletaHistorico {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@Column(name = "id_historico")
	private Long idHistorico;
	
	@Column(name = "id_maleta")
	private Long idMaleta;
	
	@Column(name = "valor")
	private double valor;
	
	public MaletaHistorico() {}
	
	public MaletaHistorico(Long idHistorico, Long idMaleta, List<Produto> produtos) {
		this.idHistorico = idHistorico;
		this.idMaleta = idMaleta;
		this.valor = converterProdutoValor(produtos);
	}
}
