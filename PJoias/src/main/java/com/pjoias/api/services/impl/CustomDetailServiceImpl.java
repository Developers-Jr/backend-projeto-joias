package com.pjoias.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pjoias.api.models.users.UserLogin;
import com.pjoias.api.repositories.UserLoginRepository;

@Service
public class CustomDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserLoginRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserLogin user = Optional.ofNullable(userRepository.findByEmail(username)).get().orElseThrow(() -> new UsernameNotFoundException("Usuario desconhecido"));
		List<GrantedAuthority> listAdminAuthorities = AuthorityUtils.createAuthorityList("ROLE_VENDEDOR", "ROLE_ADMIN");
		List<GrantedAuthority> listUserAuthorities = AuthorityUtils.createAuthorityList("ROLE_VENDEDOR");
		return new User(user.getEmail(), user.getSenha(), user.isAdmin() ? listAdminAuthorities : listUserAuthorities);
	}

}
