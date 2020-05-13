package com.pjoias.api.models.users;

public class Admin extends Pessoa{
	private String senha;
	
	public static class Builder extends Pessoa.Builder<Builder> {
		private String senha;
		
		public Builder() {}
		
		public Builder addSenha(String senha) {
			this.senha = senha;
			return this;
		}
		
		public Admin build() {
			return new Admin(this);
		}
	}
	
	private Admin(Builder builder) {
		super(builder);
		this.senha = builder.senha;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
