package com.pjoias.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjoias.api.dtos.Response;
import com.pjoias.api.dtos.RevendedorDTO;
import com.pjoias.api.exceptions.NotFoundException;
import com.pjoias.api.models.entities.Revendedor;
import com.pjoias.api.models.users.Vendedor;
import com.pjoias.api.services.RevendedorService;
import com.pjoias.api.services.VendedorService;

@RestController
@RequestMapping("v1")
public class RevendedorController {
	
	@Autowired
	private RevendedorService revendedorService;
	
	@Autowired
	private VendedorService vendedorService;
	
	@GetMapping("vendedor/revendedores")
	public ResponseEntity<Response<List<RevendedorDTO>>> listarRevendedores(Authentication auth) {
		Response<List<RevendedorDTO>> response = new Response<>();
		Long idVendedor = vendedorService.buscarPorEmail(auth.getName())
																.orElseThrow(() -> new NotFoundException("Contate um administrador"))
																.getId();
		
		List<Revendedor> revendedores = revendedorService.buscarTodosPor(idVendedor);
		List<RevendedorDTO> revendedoresDto = revendedores.stream()
															.map(r -> new RevendedorDTO(r))
															.collect(Collectors.toList());
		response.setData(revendedoresDto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("vendedor/revendedores")
	public ResponseEntity<Response<RevendedorDTO>> persistirRevendedor(@Valid @RequestBody RevendedorDTO revendedorDto, 
																		BindingResult result, Authentication auth) {
		Response<RevendedorDTO> response = new Response<>();
		Vendedor vendedor = vendedorService.buscarPorEmail(auth.getName())
															.orElseThrow(() -> new NotFoundException("Contate um administrador"));
		
		if(result.hasErrors()) {
			result.getAllErrors().stream().forEach(e -> response.addError(e.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Revendedor revendedor = new Revendedor(revendedorDto);
		revendedor.setIdVendedor(vendedor.getId());
		
		revendedorService.persistir(revendedor);
		
		response.setData(new RevendedorDTO(revendedor));
		return ResponseEntity.ok(response);
	}
	
	
	@DeleteMapping("vendedor/revendedores/{id}")
	public ResponseEntity<Void> deletarRevendedorPor(@PathVariable("id") Long idRevendedor, Authentication auth) {
		Vendedor vendedor = vendedorService.buscarPorEmail(auth.getName())
											.orElseThrow(() -> new NotFoundException("Você não possui permissão para isto!"));
		
		Revendedor revendedor = revendedorService.buscarPor(idRevendedor)
													.orElseThrow(() -> new NotFoundException("Revendedor inexistente!"));
		
		if(revendedor.getIdVendedor() == vendedor.getId()) {
			revendedorService.excluirRevendedorPorId(idRevendedor);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
}
