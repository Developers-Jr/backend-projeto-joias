package com.pjoias.api.services;

import java.util.Optional;

import com.pjoias.api.models.users.Admin;

public interface AdminService {
	public Optional<Admin> buscarPorId(Long id);
	
	public Optional<Admin> buscarPorEmailSenha(String email, String senha);
	
	public Optional<Admin> findById(Long Id);
}
