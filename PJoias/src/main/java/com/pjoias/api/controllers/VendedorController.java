package com.pjoias.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjoias.api.dtos.VendedorDTO;
import com.pjoias.api.models.users.Vendedor;
import com.pjoias.api.services.VendedorService;

@RestController
@RequestMapping("/api/vendedor")
public class VendedorController {
	
	@Autowired
	private VendedorService vendedorService;
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<VendedorDTO>> listarTodos() {
		List<Vendedor> vendedores = vendedorService.findAll();
		List<VendedorDTO> dtos = vendedores.stream()
											.map(v -> new VendedorDTO(v))
											.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<VendedorDTO> persiste(@Valid @RequestBody VendedorDTO vendedor) {
		Vendedor vend = vendedorService.persist(new Vendedor(vendedor));
		return ResponseEntity.ok(new VendedorDTO(vend));
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<VendedorDTO> findById(@PathVariable("id") Long id) {
		Optional<Vendedor> vendedor = vendedorService.findById(id);
		return ResponseEntity.ok(new VendedorDTO(vendedor.get()));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		vendedorService.deleteById(id);
		return ResponseEntity.noContent().build();
	}	

}