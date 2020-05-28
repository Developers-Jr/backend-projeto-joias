package com.pjoias.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjoias.api.dtos.VendedorDTO;
import com.pjoias.api.models.users.UserLogin;
import com.pjoias.api.models.users.Vendedor;
import com.pjoias.api.services.AdminService;
import com.pjoias.api.services.UserLoginService;
import com.pjoias.api.services.VendedorService;
import com.pjoias.api.utils.PasswordEncoder;

@RestController
@RequestMapping("v1")
public class VendedorController {
	
	@Autowired
	private VendedorService vendedorService;
	
	@Autowired
	private UserLoginService loginService;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("admin/vendedores")
	public ResponseEntity<List<VendedorDTO>> listarTodos() {
		List<Vendedor> vendedores = vendedorService.findAll();
		List<VendedorDTO> dtos = vendedores.stream()
											.map(v -> new VendedorDTO(v))
											.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping("admin/vendedores")
	public ResponseEntity<VendedorDTO> persiste(@Valid @RequestBody VendedorDTO vendedorDto) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		vendedorDto.setIdAdmin(adminService.findByEmail(user.getUsername()).getId());
		
		Vendedor vendedor = vendedorService.persist(new Vendedor(vendedorDto));
		loginService.persist(new UserLogin(vendedorDto.getNome(), vendedorDto.getEmail(), PasswordEncoder.encode(vendedorDto.getSenha()), false));
		return ResponseEntity.ok(new VendedorDTO(vendedor));
	}
	
	@GetMapping("admin/vendedores/{id}")
	public ResponseEntity<VendedorDTO> findById(@PathVariable("id") Long id) {
		Optional<Vendedor> vendedor = vendedorService.findById(id);
		return ResponseEntity.ok(new VendedorDTO(vendedor.get()));
	}
	
	@DeleteMapping("admin/vendedores/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		Optional<Vendedor> vendedor = vendedorService.findById(id);
		UserLogin login = loginService.findByEmail(vendedor.get().getEmail());
		
		if(login != null) {
			loginService.deleteById(login.getId());
		}
		
		vendedorService.deleteById(id);
		return ResponseEntity.noContent().build();
	}	

}
