package com.pjoias.api.services;

import java.util.Optional;

import com.pjoias.api.models.users.UserLogin;

public interface UserLoginService {
	UserLogin persist(UserLogin userLogin);
	
	Optional<UserLogin> findByEmail(String email);
	
	Optional<UserLogin> findById(Long id);
	
	void deleteById(Long id);
}
