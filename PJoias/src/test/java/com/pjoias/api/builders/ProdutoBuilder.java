package com.pjoias.api.builders;

import com.pjoias.api.models.entities.Maleta;
import com.pjoias.api.models.entities.Produto;

public class ProdutoBuilder {
	private Produto produto;
	
	private ProdutoBuilder() {}
	
	public static ProdutoBuilder umProduto() {
		ProdutoBuilder builder = new ProdutoBuilder();
		builder.produto = new Produto();
		builder.produto.setNome("br");
		builder.produto.setValor(50);
		return builder;
	}
	
	public ProdutoBuilder comMaleta(Maleta maleta) {
		produto.setIdMaleta(maleta.getId());
		return this;
	}
	
	public Produto agora() {
		return produto;
	}
}
