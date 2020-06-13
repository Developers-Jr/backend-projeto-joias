package com.pjoias.api.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Response<MaletaDTO>> persiste(@Valid @RequestBody MaletaDTO maletaDto, BindingResult result, Authentication authentication) {
		Response<MaletaDTO> response = new Response<>();
		Long idAdmin = adminService.findByEmail(authentication.getName()).get().getId();
		
		this.validarNomeMaleta(maletaDto.getNome(), result);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(err -> response.addError(err.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		

		Maleta maleta = new Maleta(maletaDto);
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
	 * Busca maletas atribuidas a um vendedor
	 * 
	 * @param vendedorId
	 * @return ResponseEntity<Response<List<MaletaDTO>>>
	 */
	@GetMapping("admin/maletas/busca")
	public ResponseEntity<Response<List<MaletaDTO>>> buscarMaletasPorVendedor(@RequestParam("vendedor") Long vendedorId) {
		Response<List<MaletaDTO>> response = new Response<>();
		List<Maleta> maletas = maletaService.buscarPorIdVendedor(vendedorId);
		
		List<MaletaDTO> listMaletaDto = maletas.stream().map(m -> new MaletaDTO(m)).collect(Collectors.toList());
		response.setData(listMaletaDto);
		return ResponseEntity.ok(response);
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
	@PutMapping("vendedor/maletas/{id}")
	public ResponseEntity<Response<MaletaDTO>> atualizarMaleta(@PathVariable("id") Long id, @RequestBody MaletaDTO maletaDto, Authentication authentication) {
		Response<MaletaDTO> response = new Response<>();
		Optional<Maleta> maleta = maletaService.buscarPorId(id);
		
		if(maleta.isPresent()) {
			this.atualizar(maleta.get(), maletaDto, authentication);
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
	private void atualizar(Maleta maleta, MaletaDTO maletaDto, Authentication authentication) {
		List<GrantedAuthority> admin = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
		
		if(authentication.getAuthorities().contains(admin.get(0))) {
			maleta.setNome(maletaDto.getNome());
			maleta.setFechada(maletaDto.isFechada());
		} else {
			if(maletaDto.isFechada()) {
				maleta.setFechada(maletaDto.isFechada());
			}
		}
	}
	
	private void validarNomeMaleta(String nome, BindingResult result) {
		Optional<Maleta> maleta = maletaService.buscarPorNome(nome);
		
		if(maleta.isPresent()) {
			result.addError(new ObjectError("maletaExistente", "Já existe uma maleta com esse nome"));
		}
	}
}
