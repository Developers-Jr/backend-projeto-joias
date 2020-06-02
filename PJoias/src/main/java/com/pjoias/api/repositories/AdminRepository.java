package com.pjoias.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pjoias.api.models.users.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
	Optional<Admin> findById(Long id);
	
	Optional<Admin> findByEmail(String email);
}
