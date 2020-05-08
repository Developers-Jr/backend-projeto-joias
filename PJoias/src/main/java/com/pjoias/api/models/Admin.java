package com.pjoias.api.models;

public class Admin extends Pessoa{
	private String senha;

	public Admin(Long id, String nome, String telefone, String email) {
		super(id, nome, telefone, email);
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
