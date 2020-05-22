package com.pjoias.api.security.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
	
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String senha;
}
