package com.pjoias.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjoias.api.dtos.MaletaDTO;
import com.pjoias.api.dtos.Response;
import com.pjoias.api.models.entities.Maleta;
import com.pjoias.api.services.AdminService;
import com.pjoias.api.services.MaletaService;

@RestController
@RequestMapping("v1")
public class MaletaController {
	
	@Autowired
	private MaletaService maletaService;
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * Cadastra maleta 
	 * 
	 * @param maletaDto
	 * @param authentication
	 * @return ResponseEntity<Response<MaletaDTO>>
	 */
	@PostMapping("admin/maletas")
	public ResponseEntity<Response<MaletaDTO>> persiste(@Valid @RequestBody MaletaDTO maletaDto, Authentication authentication) {
		Response<MaletaDTO> response = new Response<>();
		Maleta maleta = new Maleta(maletaDto);
		
		Long idAdmin = adminService.findByEmail(authentication.getName()).getId();
		if(idAdmin == null) {
			response.addError("Admin inválido");
			return ResponseEntity.badRequest().body(response);
		}
		
		maleta.setId_admin(idAdmin);
		
		maletaService.persist(maleta);
		response.setData(new MaletaDTO(maleta));
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Lista todas maletas 
	 * 
	 * @param authentication
	 * @return ResponseEntity<Response<List<MaletaDTO>>>
	 */
	@GetMapping("admin/maletas")
	public ResponseEntity<Response<List<MaletaDTO>>> listarTodas(Authentication authentication) {
		Response<List<MaletaDTO>> response = new Response<>();
		List<MaletaDTO> maletas = maletaService.buscarTodos()
												.stream()
												.map(m -> new MaletaDTO(m))
												.collect(Collectors.toList());
		
		if(maletas != null) {
			
			response.setData(maletas);
			return ResponseEntity.ok(response);
		}
		
		response.addError("Falha na busca de maletas!");
		return ResponseEntity.badRequest().body(response);
	}
	
	/**
	 * Retorna uma maleta de acordo com id
	 * 
	 * @param id
	 * @return ResponseEntity<Response<MaletaDTO>>
	 */
	@GetMapping("admin/maletas/{id}")
	public ResponseEntity<Response<MaletaDTO>> buscarPorId(@PathVariable Long id) {
		Response<MaletaDTO> response = new Response<>();
		Optional<Maleta> maleta = maletaService.buscarPorId(id);
		
		if(maleta.isPresent()) {
			response.setData(new MaletaDTO(maleta.get()));
			return ResponseEntity.ok(response);
		}
		
		response.addError("Maleta inexistente!");
		return ResponseEntity.badRequest().body(response);
	}
	
	/**
	 * Deleta maleta de acordo com admin
	 * 
	 * @param id
	 * @return ResponseEntity<Response<Void>>
	 */
	@DeleteMapping("admin/maletas/{id}")
	public ResponseEntity<Response<Void>> deletarPorId(@PathVariable("id") Long id) {
		maletaService.deletarPorId(id);
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Atualiza maleta
	 * 
	 * @param id
	 * @param maletaDto
	 * @return ResponseEntity<Response<MaletaDTO>>
	 */
	@PutMapping("admin/maletas/{id}")
	public ResponseEntity<Response<MaletaDTO>> atualizar(@PathVariable("id") Long id, @RequestBody MaletaDTO maletaDto) {
		Response<MaletaDTO> response = new Response<>();
		Optional<Maleta> maleta = maletaService.buscarPorId(id);
		
		if(maleta.isPresent()) {
			this.atualizar(maleta.get(), maletaDto);
			maletaService.persist(maleta.get());
			
			response.setData(new MaletaDTO(maleta.get()));
			return ResponseEntity.ok(response);
		}
		
		response.addError("Maleta não encontrada");
		return ResponseEntity.badRequest().body(response);
	}
	
	/**
	 * Regras para atualizar maleta
	 * 
	 * @param maleta
	 * @param maletaDto
	 */
	private void atualizar(Maleta maleta, MaletaDTO maletaDto) {
		maleta.setNome(maletaDto.getNome());
		maleta.setStatus_maleta(maletaDto.isStatus_maleta());
	}
}
