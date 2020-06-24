package com.pjoias.api.services;

import java.util.Optional;

import com.pjoias.api.models.users.UserLogin;

public interface UserLoginService {
	UserLogin persistir(UserLogin userLogin);
	
	Optional<UserLogin> buscarPorEmail(String email);
	
	Optional<UserLogin> buscarPorId(Long id);
	
	void deletarPorId(Long id);
}
