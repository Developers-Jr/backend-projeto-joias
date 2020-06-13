package com.pjoias.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjoias.api.models.users.Admin;
import com.pjoias.api.repositories.AdminRepository;
import com.pjoias.api.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Optional<Admin> findById(Long id) {
		return adminRepository.findById(id);
	}

	@Override
	public Optional<Admin> findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}
}
