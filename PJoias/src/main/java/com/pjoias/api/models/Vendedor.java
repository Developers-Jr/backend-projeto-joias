package com.pjoias.api.models;

public class Vendedor extends Pessoa{
	private String senha;
	
	public Vendedor(Long id, String nome, String telefone, String email) {
		super(id, nome, telefone, email);
		// TODO Auto-generated constructor stub
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
