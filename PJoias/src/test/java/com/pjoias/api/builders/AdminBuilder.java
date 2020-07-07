package com.pjoias.api.builders;

import com.pjoias.api.models.users.Admin;

public class AdminBuilder {
	private Admin admin;
	private AdminBuilder() {}
	
	public static AdminBuilder umAdmin() {
		AdminBuilder builder = new AdminBuilder();
		builder.admin = new Admin();
		builder.admin.setNome("admin");
		builder.admin.setEmail("admin@gmail.com");
		builder.admin.setSobrenome("sobrenome");
		return builder;
	}
	
	public Admin agora() {
		return admin;
	}
}
