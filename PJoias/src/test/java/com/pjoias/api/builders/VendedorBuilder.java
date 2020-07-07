package com.pjoias.api.builders;

import com.pjoias.api.models.users.Admin;
import com.pjoias.api.models.users.Vendedor;

public class VendedorBuilder {
	private Vendedor vendedor;
	
	private VendedorBuilder() {}
	
	public static VendedorBuilder umVendedor(Admin admin) {
		VendedorBuilder builder = new VendedorBuilder();
		builder.vendedor = new Vendedor();
		builder.vendedor.setEmail("vendedor@gmail.com");
		builder.vendedor.setIdAdmin(admin.getId());
		builder.vendedor.setNome("vendedor");
		builder.vendedor.setSobrenome("sobrenome");
		builder.vendedor.setTelefone("9999999");
		return builder;
	}
	
	public Vendedor agora() {
		return vendedor;
	}
}
