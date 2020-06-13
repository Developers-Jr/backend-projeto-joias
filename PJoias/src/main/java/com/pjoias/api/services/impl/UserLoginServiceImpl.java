package com.pjoias.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjoias.api.models.users.UserLogin;
import com.pjoias.api.repositories.UserLoginRepository;
import com.pjoias.api.services.UserLoginService;

@Service
public class UserLoginServiceImpl implements UserLoginService {

	@Autowired
	private UserLoginRepository loginRepository;
	
	@Override
	public UserLogin persist(UserLogin userLogin) {
		return loginRepository.save(userLogin);
	}

	@Override
	public Optional<UserLogin> findByEmail(String email) {
		return loginRepository.findByEmail(email);
	}

	@Override
	public Optional<UserLogin> findById(Long id) {
		return loginRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		loginRepository.deleteById(id);
	}

}
