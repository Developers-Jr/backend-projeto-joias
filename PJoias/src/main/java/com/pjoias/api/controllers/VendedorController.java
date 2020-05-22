package com.pjoias.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjoias.api.dtos.VendedorDTO;
import com.pjoias.api.models.users.Admin;
import com.pjoias.api.models.users.Vendedor;
import com.pjoias.api.services.AdminService;
import com.pjoias.api.services.VendedorService;

@RestController
@RequestMapping("/api/vendedor")
public class VendedorController {
	
	@Autowired
	private VendedorService vendedorService;
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping
	public ResponseEntity<List<VendedorDTO>> listarTodos(@RequestAttribute("idUsuarioLogado") Long idUsuarioLogado) {
		Admin admin = adminService.findById(idUsuarioLogado).get();
		List<Vendedor> vendedores = vendedorService.findAllByIdAdmin(admin.getId());
		List<VendedorDTO> dtos = vendedores.stream()
											.map(v -> new VendedorDTO(v))
											.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping
	public ResponseEntity<VendedorDTO> persiste(@Valid @RequestBody VendedorDTO vendedor) {
		Vendedor vend = vendedorService.persist(new Vendedor(vendedor));
		return ResponseEntity.ok(new VendedorDTO(vend));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VendedorDTO> findById(@PathVariable("id") Long id) {
		Optional<Vendedor> vendedor = vendedorService.findById(id);
		return ResponseEntity.ok(new VendedorDTO(vendedor.get()));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		vendedorService.deleteById(id);
		return ResponseEntity.noContent().build();
	}	

}
