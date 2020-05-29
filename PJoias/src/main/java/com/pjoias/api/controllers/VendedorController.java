package com.pjoias.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjoias.api.dtos.Response;
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
		List<Vendedor> vendedores = vendedorService.buscarTodos();
		List<VendedorDTO> dtos = vendedores.stream()
											.map(v -> new VendedorDTO(v))
											.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping("admin/vendedores")
	public ResponseEntity<Response<VendedorDTO>> persistir(@Valid @RequestBody VendedorDTO vendedorDto, BindingResult result, Authentication authentication) {
		Response<VendedorDTO> response = new Response<>();
		
		vendedorDto.setIdAdmin(adminService.findByEmail(authentication.getName()).getId());
		this.verificarEmailExistente(vendedorDto.getEmail(), result);
		this.verificarTelefoneExistente(vendedorDto.getTelefone(), result);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(err -> response.addError(err.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Vendedor vendedor = vendedorService.persistir(new Vendedor(vendedorDto));
		loginService.persist(new UserLogin(vendedorDto.getNome(), vendedorDto.getEmail(), PasswordEncoder.encode(vendedorDto.getSenha()), false));
		response.setData(new VendedorDTO(vendedor));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("admin/vendedores/{id}")
	public ResponseEntity<VendedorDTO> findById(@PathVariable("id") Long id) {
		Optional<Vendedor> vendedor = vendedorService.buscarPorId(id);
		return ResponseEntity.ok(new VendedorDTO(vendedor.get()));
	}
	
	@PutMapping("admin/vendedores/{id}")
	public ResponseEntity<Response<VendedorDTO>> atualizar(@Valid @RequestBody VendedorDTO vendedorDTO, 
													@PathVariable("id") Long id, BindingResult result) {
		
		Response<VendedorDTO> response = new Response<>();
		Vendedor vendedor = vendedorService.buscarPorId(id).get();
		UserLogin loginVendedor = loginService.findByEmail(vendedor.getEmail()).get();
		
		this.atualizarVendedor(vendedorDTO, vendedor, result, loginVendedor);
		if(result.hasErrors()) {
			result.getAllErrors().forEach(err -> response.addError(err.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(response);
		}
		
		vendedorService.persistir(vendedor);
		response.setData(new VendedorDTO(vendedor));
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("admin/vendedores/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable("id") Long id) {
		Optional<Vendedor> vendedor = vendedorService.buscarPorId(id);
		UserLogin login = loginService.findByEmail(vendedor.get().getEmail()).get();
		
		if(login != null) {
			loginService.deleteById(login.getId());
		}
		
		vendedorService.deletarPorId(id);
		return ResponseEntity.noContent().build();
	}	
	
	private void verificarEmailExistente(String email, BindingResult result) {
		vendedorService.buscarPorEmail(email)
			.ifPresent(erro -> result.addError(new ObjectError("emailInvalido", "Este email já foi cadastrado!")));
		
	}
	
	private void verificarTelefoneExistente(String telefone, BindingResult result) {
		vendedorService.buscarPorTelefone(telefone)
			.ifPresent(erro -> result.addError(new ObjectError("telefoneExistente", "Este telefone já foi cadastrado!")));
		
	}
	
	private void atualizarVendedor(VendedorDTO vendedorDTO, Vendedor vendedor, BindingResult result, UserLogin login) {
		if(!vendedorDTO.getEmail().equals(vendedor.getEmail())) {
			this.verificarEmailExistente(vendedorDTO.getEmail(), result);
			
			vendedor.setEmail(vendedorDTO.getEmail());
			login.setEmail(vendedorDTO.getEmail());
		}
		
		if(!vendedorDTO.getTelefone().equals(vendedor.getTelefone())) {
			this.verificarTelefoneExistente(vendedorDTO.getTelefone(), result);
			
			vendedor.setTelefone(vendedorDTO.getTelefone());
		}
		
		if(vendedorDTO.getSenha() != null && vendedorDTO.getSenha() != "") {
			login.setSenha(PasswordEncoder.encode(vendedorDTO.getSenha()));
		}
	}
}
