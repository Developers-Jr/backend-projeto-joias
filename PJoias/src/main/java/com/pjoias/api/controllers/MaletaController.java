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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pjoias.api.dtos.MaletaDTO;
import com.pjoias.api.dtos.Response;
import com.pjoias.api.exceptions.NotFoundException;
import com.pjoias.api.models.entities.Maleta;
import com.pjoias.api.models.entities.MaletaAtual;
import com.pjoias.api.models.entities.MaletaAtualId;
import com.pjoias.api.models.users.Vendedor;
import com.pjoias.api.services.AdminService;
import com.pjoias.api.services.MaletaAtualService;
import com.pjoias.api.services.MaletaService;
import com.pjoias.api.services.VendedorService;

@RestController
@RequestMapping("v1")
public class MaletaController {
	
	@Autowired
	private MaletaService maletaService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MaletaAtualService maletaAtualService;
	
	@Autowired
	private VendedorService vendedorService;
	
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
	 * @param authenticationResponseEntity<Response<MaletaDTO>>
	 * @return ResponseEntity<Response<List<MaletaDTO>>>
	 */
	@GetMapping("admin/maletas")
	public ResponseEntity<Response<List<MaletaDTO>>> listarTodas() {
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
		return ResponseEntity.notFound().build();
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
		
		listMaletaDto.stream().forEach(mdto -> this.setValorVendido(mdto, vendedorId));
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
	public ResponseEntity<Response<String>> deletarPorId(@PathVariable("id") Long id) {
		Response<String> response = new Response<>();
		
		if(!maletaService.buscarPorId(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		try {
			maletaService.deletarPorId(id);
		} catch(Exception e) {
			response.addError("A maleta está atribuida em um histórico, não é possível a deletar!");
			return ResponseEntity.badRequest().body(response);
		}
		
		
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Admin atualiza maleta
	 * 
	 * @param id
	 * @param maletaDto
	 * @return ResponseEntity<Response<MaletaDTO>>
	 */
	@PutMapping("admin/maletas/{id}")
	public ResponseEntity<Response<MaletaDTO>> atualizarMaletaAdmin(@PathVariable("id") Long id, @RequestBody MaletaDTO maletaDto) {
		Response<MaletaDTO> response = new Response<>();
		Optional<Maleta> maleta = maletaService.buscarPorId(id);
		
		if(maleta.isPresent()) {
			maleta.get().setFechada(maletaDto.isFechada());
			maletaService.persist(maleta.get());
			
			response.setData(new MaletaDTO(maleta.get()));
			return ResponseEntity.ok(response);
		}
		
		response.addError("Maleta não encontrada");
		return ResponseEntity.badRequest().body(response);
	}
	
	/**
	 * Vendedor faz o fechamento da maleta
	 * 
	 * @return ResponseEntity<Response<MaletaDTO>>
	 */
	@PutMapping("vendedor/maletas/{id}")
	public ResponseEntity<Response<String>> atualizarMaletaVendedor(@PathVariable("id") Long idMaleta, @RequestBody MaletaDTO maletaDto, Authentication authentication) {
		Response<String> response = new Response<>();
		
		Vendedor vendedor = vendedorService.buscarPorEmail(authentication.getName())
															.orElseThrow(() -> new NotFoundException("Você precisa ser um vendedor!"));
		
		MaletaAtual maletaAtual = maletaAtualService.buscarPorId(new MaletaAtualId(vendedor.getId(), idMaleta))
																	.orElseThrow(() -> new NotFoundException("Esta maleta não esta atribuída a você!"));
		
		if(maletaDto.isFechada()) {
			Maleta maleta = maletaService.buscarPorId(maletaAtual.getIdMaletaAtual().getIdMaleta())
																.orElseThrow(() ->  new NotFoundException("Maleta não encontrada!"));
			
			
			maletaAtual.setFechada(true);
			maleta.setFechada(true);
			
			maletaService.persist(maleta);
			maletaAtualService.persistir(maletaAtual);
			return ResponseEntity.ok(null);
		} 
		
		response.addError("Esta maleta já foi fechada!");
		return ResponseEntity.badRequest().body(response);
	}
	
	/**
	 * Valida o nome da maleta
	 * 
	 * @param nome
	 * @param result
	 */
	private void validarNomeMaleta(String nome, BindingResult result) {
		Optional<Maleta> maleta = maletaService.buscarPorNome(nome);
		
		if(maleta.isPresent()) {
			result.addError(new ObjectError("maletaExistente", "Já existe uma maleta com esse nome"));
		}
	}
	
	/**
	 * Define o valor vendido para o DTO 
	 * 
	 * @param maletaDto
	 * @param vendedorId
	 */
	private void setValorVendido(MaletaDTO maletaDto, Long vendedorId) {
		MaletaAtual maletaAtual = maletaAtualService.buscarPorId(new MaletaAtualId(vendedorId, maletaDto.getId()))
							.orElseThrow(() -> new NotFoundException("Alguma maleta não foi encontrada!"));
		
		maletaDto.setValorVendido(maletaAtual.getValorVendido());
	}
}
