package com.pjoias.api.models;

public class Pessoa {
	private Long id;
	private String nome;
	private String telefone;
	private String email;
	
	public static class Builder<T extends Builder<T>> {
		private Long id;
		private String nome;
		private String telefone;
		private String email;
		
		public Builder() {}
		
		public Builder<T> addId(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder<T> addNome(String nome) {
			this.nome = nome;
			return this;
		}
		
		public Builder<T> addTelefone(String telefone) {
			this.telefone = telefone;
			return this;
		}
		
		public Builder<T> addEmail(String email) {
			this.email = email;
			return this;
		}
		
		public Pessoa build() {
			return new Pessoa(this);
		}
	}
	
	protected Pessoa(Builder<?> builder) {
		this.id = builder.id;
		this.email = builder.email;
		this.nome = builder.nome;
		this.telefone = builder.telefone;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
