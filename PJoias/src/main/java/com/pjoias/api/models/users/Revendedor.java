package com.pjoias.api.models.users;

public class Revendedor extends Pessoa{
	private String sobrenome;
	private String rg;
	private String cpf;
	private String cep;
	
	public static class Builder extends Pessoa.Builder<Builder>{
		private String sobrenome;
		private String rg;
		private String cpf;
		private String cep;
		
		public Builder() {}
		
		public Builder addSobrenome(String sobrenome) {
			this.sobrenome = sobrenome;
			return this;
		}
		
		public Builder addRg(String rg) {
			this.rg = rg;
			return this;
		}
		
		public Builder addCpf(String cpf) {
			this.cpf = cpf;
			return this;
		}
		
		public Builder addCep(String cep) {
			this.cep = cep;
			return this;
		}
		
		public Revendedor build() {
			return new Revendedor(this);
		}
	}
	
	private Revendedor(Builder builder) {
		super(builder);
		this.sobrenome = builder.sobrenome;
		this.rg = builder.rg;
		this.cpf = builder.cpf;
		this.cep = builder.cep;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
