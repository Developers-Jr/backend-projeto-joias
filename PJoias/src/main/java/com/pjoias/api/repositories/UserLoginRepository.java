package com.pjoias.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pjoias.api.models.users.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long>{
	Optional<UserLogin> findByEmail(String email);
}
