package com.pjoias.api.models.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_login")
public class UserLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Column(name="nome")
	private String nome;
	
	@Email
	@Column(unique = true)
	private String email;
	
	@NotEmpty
	@Column(name="senha")
	private String senha;
		
	@Column(name="admin")
	private boolean admin;
	
	public UserLogin() {}
	
	public UserLogin(String nome, String email, String senha, boolean admin) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.admin = admin;
	}

}
