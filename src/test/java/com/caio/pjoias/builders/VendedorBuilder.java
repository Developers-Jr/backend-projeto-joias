package com.caio.pjoias.builders;

import com.caio.pjoias.models.Vendedor;

public class VendedorBuilder {
    private Vendedor vendedor;

    private VendedorBuilder(){}

    public static VendedorBuilder umVendedor() {
        var builder = new VendedorBuilder();
        builder.vendedor = new Vendedor();
        builder.vendedor.setUid("vendedoruid");
        builder.vendedor.setEmail("vendedor@gmail.com");
        builder.vendedor.setNome("vendedor");
        builder.vendedor.setSobrenome("sobrenome");
        builder.vendedor.setSenha("senhavendedor");
        return builder;
    }

    public Vendedor agora() {
        return this.vendedor;
    }
}
