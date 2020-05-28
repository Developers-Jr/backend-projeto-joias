package com.pjoias.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pjoias.api.models.users.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Long>{
	UserLogin findByEmail(String email);
}
