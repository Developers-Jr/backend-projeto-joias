package com.pjoias.api.builders;

import com.pjoias.api.models.entities.Revendedor;
import com.pjoias.api.models.users.Vendedor;

public class RevendedorBuilder {
	private Revendedor revendedor;
	
	private RevendedorBuilder() {}
	
	public static RevendedorBuilder umRevendedor() {
		RevendedorBuilder builder = new RevendedorBuilder();
		builder.revendedor = new Revendedor();
		builder.revendedor.setNome("revendedor");
		builder.revendedor.setCpf("55566677750");
		builder.revendedor.setRg("8888888");
		builder.revendedor.setTelefone("19977753826");
		return builder;
	}
	
	public RevendedorBuilder comVendedor(Vendedor vendedor) {
		revendedor.setIdVendedor(vendedor.getId());
		return this; 
	}
	
	public Revendedor agora() {
		return revendedor;
	}
}
