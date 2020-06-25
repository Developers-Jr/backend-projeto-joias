package com.pjoias.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import com.pjoias.api.models.entities.MaletaAtual;
import com.pjoias.api.models.entities.MaletaAtualId;
import com.pjoias.api.models.entities.Produto;
import com.pjoias.api.models.users.Vendedor;
import com.pjoias.api.services.MaletaAtualService;
import com.pjoias.api.services.MaletaService;
import com.pjoias.api.services.ProdutoService;
import com.pjoias.api.services.VendedorService;

@RestController
@RequestMapping("v1")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private VendedorService vendedorService;
	
	@Autowired
	private MaletaService maletaService;
	
	@Autowired
	private MaletaAtualService maletaAtualService;
	
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
		this.validarProdutoExistente_eMaletaFechada(id);
		
		produtoService.excluirPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Delete de produto para vendedor
	 * 
	 * @param id
	 * @return ResponseEntity<Void>
	 */
	@DeleteMapping("vendedor/produtos/{id}")
	public ResponseEntity<Void> deletarProdutoVendedor(@PathVariable("id") Long id, Authentication auth) {
		Produto produto = produtoService.buscarPorId(id)
										.orElseThrow(() -> new NotFoundException("Produto inexistente!"));
		
		Maleta maleta = maletaService.buscarPorId(produto.getIdMaleta())	
												.orElseThrow(() -> new NotFoundException("Produto inexistente!"));
		
		Vendedor vendedor = vendedorService.buscarPorEmail(auth.getName())
												.orElseThrow(() -> new NotFoundException("Contate um administrador!"));
		
		if(!maleta.isFechada()) {
			MaletaAtualId maletaAtualId = new MaletaAtualId(vendedor.getId(), maleta.getId());
			MaletaAtual maletaAtual = maletaAtualService.buscarPorId(maletaAtualId)
													.orElseThrow(() -> new NotFoundException("Este produto não pertence a sua maleta!"));
			
			double tempValor = maletaAtual.getValorVendido() + produto.getValor();
			maletaAtual.setValorVendido(tempValor);
			

			produtoService.excluirPorId(id);
			maletaAtualService.persistir(maletaAtual);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
	}
	
	/**
	 * Valida se o produto possui um valor e se possui uma maleta
	 * 
	 * @param produtoDto
	 * @param result
	 */
	private void validarValorEMaleta(ProdutoDTO produtoDto, BindingResult result) {
		maletaService.buscarPorId(produtoDto.getIdMaleta())
											.orElseThrow(() -> new NotFoundException("Maleta inexistente!"));
		
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
	public boolean validarProdutoExistente_eMaletaFechada(Long id) {
		Long idMaleta = produtoService.buscarPorId(id)
										.orElseThrow(() -> new NotFoundException("Produto não encontrado!"))
										.getIdMaleta();
		
		Maleta maleta = maletaService.buscarPorId(idMaleta)
										.orElseThrow(() -> new NotFoundException("Maleta inexistente!"));
		
		return maleta.isFechada();
	}
}
