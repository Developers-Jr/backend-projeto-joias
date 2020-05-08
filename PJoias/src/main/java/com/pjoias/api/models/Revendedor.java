package com.pjoias.api.models;

public class Revendedor extends Pessoa{
	private String sobrenome;
	private String rg;
	private String cpf;
	private String cep;
	
	public Revendedor(Long id, String nome, String telefone, 
			String email, String sobrenome, String rg, String cpf, String cep) {
		super(id, nome, telefone, email);
		
		this.sobrenome = sobrenome;
		this.cep = cep;
		this.cpf = cpf;
		this.rg = rg;
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
