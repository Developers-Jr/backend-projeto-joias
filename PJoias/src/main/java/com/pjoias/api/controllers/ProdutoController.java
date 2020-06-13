package com.pjoias.api.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pjoias.api.dtos.ProdutoDTO;
import com.pjoias.api.dtos.Response;
import com.pjoias.api.exceptions.NotFoundException;
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
	
	/**
	 * Persiste um produto
	 * 
	 * @param produtoDto
	 * @param result
	 * @return ResponseEntity<Response<ProdutoDTO>>
	 */
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
	
	/**
	 * Deleta produto
	 * 
	 * @param id
	 * @param result
	 * @return
	 */
	@DeleteMapping("admin/produtos/{id}")
	public ResponseEntity<Void> deletarProduto(@PathVariable("id") Long id) {
		this.validarProdutoExistente(id);
		
		produtoService.excluirPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Valida se o produto possui um valor e se possui uma maleta
	 * 
	 * @param produtoDto
	 * @param result
	 */
	private void validarValorEMaleta(ProdutoDTO produtoDto, BindingResult result) {
		Optional<Maleta> maleta = maletaService.buscarPorId(produtoDto.getIdMaleta());
		
		if(maleta.get() == null) {
			result.addError(new ObjectError("maletaInexistente", "A maleta indicada não existe"));
		}
		
		if(produtoDto.getValor() < 0) {
			result.addError(new ObjectError("valorNegativo", "O valor não pode ser negativo"));
		}
	}
	
	/**
	 * Valida se produto existe
	 * 
	 * @param id
	 * @param result
	 */
	public void validarProdutoExistente(Long id) {
		Optional<Produto> produto = produtoService.buscarPorId(id);
		
		if(!produto.isPresent()) {
			throw new NotFoundException();
		}
	}
}
