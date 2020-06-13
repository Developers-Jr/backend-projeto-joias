package com.pjoias.api.models.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_login")
public class UserLogin {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@NotEmpty
	@Column(name="nome")
	private String nome;
	
	@Email
	@Column(name="email", unique = true)
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
