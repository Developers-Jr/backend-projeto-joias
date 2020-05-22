package com.pjoias.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pjoias.api.models.users.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	Optional<Admin> findByEmailAndSenha(String email, String senha);
	
	Optional<Admin> findById(Long id);
}
