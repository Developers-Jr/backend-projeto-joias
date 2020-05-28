package com.pjoias.api.services;

import java.util.Optional;

import com.pjoias.api.models.users.Admin;

public interface AdminService {
	public Admin findByEmail(String email);
	
	public Optional<Admin> findById(Long Id);
}
