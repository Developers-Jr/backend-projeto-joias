package com.pjoias.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjoias.api.dtos.ProdutoDTO;
import com.pjoias.api.dtos.Response;
import com.pjoias.api.models.entities.Maleta;
import com.pjoias.api.models.entities.Produto;
import com.pjoias.api.services.MaletaService;
import com.pjoias.api.services.ProdutoService;

@RestController
@RequestMapping("v1")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private MaletaService maletaService;
	
	@PostMapping("admin/produtos")
	public ResponseEntity<Response<ProdutoDTO>> persistir(@Valid @RequestBody ProdutoDTO produtoDto, BindingResult result) {
		Response<ProdutoDTO> response = new Response<>();
		this.validarValorEMaleta(produtoDto, result);
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(err -> response.addError(err.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Produto produto = produtoService.persistir(new Produto(produtoDto));
		response.setData(new ProdutoDTO(produto));
		
		return ResponseEntity.ok(response);
	}
	
	private void validarValorEMaleta(ProdutoDTO produtoDto, BindingResult result) {
		Optional<Maleta> maleta = maletaService.buscarPorId(produtoDto.getIdMaleta());
		
		if(maleta.isEmpty()) {
			result.addError(new ObjectError("maletaInexistente", "A maleta indicada não existe"));
		}
		
		if(produtoDto.getValor() < 0) {
			result.addError(new ObjectError("valorNegativo", "O valor não pode ser negativo"));
		}
	}
}
