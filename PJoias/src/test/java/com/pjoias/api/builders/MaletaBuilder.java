package com.pjoias.api.builders;

import com.pjoias.api.models.entities.Maleta;

public class MaletaBuilder {
	private Maleta maleta;
	
	private MaletaBuilder() {}
	
	public static MaletaBuilder umaMaleta() {
		MaletaBuilder builder = new MaletaBuilder();
		builder.maleta = new Maleta();
		builder.maleta.setNome("maleta");
		return builder;
	}
	
	public Maleta agora() {
		return maleta;
	}
}
