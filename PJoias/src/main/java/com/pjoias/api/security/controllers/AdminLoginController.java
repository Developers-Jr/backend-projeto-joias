package com.pjoias.api.security.controllers;

import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pjoias.api.models.users.Admin;
import com.pjoias.api.security.dtos.AdminDto;
import com.pjoias.api.services.AdminService;

@RestController
@RequestMapping("/api/login")
public class AdminLoginController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping
	public void login(@Valid @RequestBody AdminDto dto, HttpServletResponse response) throws Exception {
		Optional<Admin> admin = adminService.buscarPorEmailSenha(dto.getEmail(), dto.getSenha());
		if(!admin.isPresent()) {
			throw new Exception();
		}
		
		String jwt = JWT.create()
			.withClaim("idUsuario", admin.get().getId())
			.sign(Algorithm.HMAC256("caiomello"));
		
		Cookie cookie = new Cookie("token", jwt);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setMaxAge(60 * 30);
		response.addCookie(cookie);
	}
}
