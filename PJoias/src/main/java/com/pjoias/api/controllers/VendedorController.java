package com.pjoias.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.pjoias.api.dtos.MaletaHistoricoDTO;
import com.pjoias.api.dtos.Response;
import com.pjoias.api.dtos.VendedorDTO;
import com.pjoias.api.exceptions.NotFoundException;
import com.pjoias.api.models.entities.Historico;
import com.pjoias.api.models.entities.Maleta;
import com.pjoias.api.models.entities.MaletaAtual;
import com.pjoias.api.models.entities.MaletaAtualId;
import com.pjoias.api.models.entities.MaletaHistorico;
import com.pjoias.api.models.entities.Produto;
import com.pjoias.api.models.users.UserLogin;
import com.pjoias.api.models.users.Vendedor;
import com.pjoias.api.services.AdminService;
import com.pjoias.api.services.HistoricoService;
import com.pjoias.api.services.MaletaAtualService;
import com.pjoias.api.services.MaletaHistoricoService;
import com.pjoias.api.services.MaletaService;
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
	private HistoricoService historicoService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MaletaService maletaService;
	
	@Autowired
	private MaletaHistoricoService maletaHistoricoService;
	
	@Autowired
	private MaletaAtualService maletaAtualService;
	
	@GetMapping("admin/vendedores")
	public ResponseEntity<Response<List<VendedorDTO>>> listarTodos(Authentication authentication) {
		Response<List<VendedorDTO>> response = new Response<>();
		List<VendedorDTO> vendedoresDto = new ArrayList<>();
		
		List<Vendedor> vendedores = vendedorService.buscarTodos();
		for(Vendedor vendedor : vendedores) {
			double valorTotal = this.calculaValorMaletas(vendedor);
			
			VendedorDTO vendedorDto = new VendedorDTO(vendedor);
			vendedorDto.setValorTotalMaletas(valorTotal);
			
			vendedoresDto.add(vendedorDto);
		}
		
		response.setData(vendedoresDto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("admin/vendedores/{id}")
	public ResponseEntity<Response<VendedorDTO>> buscarPorId(@PathVariable("id") Long id) {
		Response<VendedorDTO> response = new Response<>();
		Vendedor vendedor = vendedorService.buscarPorId(id).orElseThrow(() -> new NotFoundException("Vendedor não encontrado!"));
		VendedorDTO vendedorDto = new VendedorDTO(vendedor);
		
		vendedorDto.setValorTotalMaletas(this.calculaValorMaletas(vendedor));
		response.setData(vendedorDto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("admin/vendedores/historico/{id}")
	public ResponseEntity<Response<List<MaletaHistoricoDTO>>> buscarHistoricoPorIdVendedor(@PathVariable("id") Long idVendedor) {
		Response<List<MaletaHistoricoDTO>> response = new Response<>();
		
		vendedorService.buscarPorId(idVendedor).orElseThrow(() -> new NotFoundException("Vendedor não encontrado!"));
		
		List<MaletaHistorico> maletaHistorico = maletaHistoricoService.buscarPorIdVendedor(idVendedor);
		List<MaletaHistoricoDTO> maletaHistoricoDto = maletaHistorico.stream()
																		.map(mh -> new MaletaHistoricoDTO(mh))
																		.collect(Collectors.toList()); 
		
		response.setData(maletaHistoricoDto);
		return ResponseEntity.ok(response);
	}
	
	
	@PostMapping("admin/vendedores")
	public ResponseEntity<Response<VendedorDTO>> persistir(@Valid @RequestBody VendedorDTO vendedorDto, BindingResult result, Authentication authentication) {
		Response<VendedorDTO> response = new Response<>();
		
		vendedorDto.setIdAdmin(adminService.findByEmail(authentication.getName())
											.orElseThrow(() -> new NotFoundException("Ocorreu um problema, fale com o admin do sistema!"))
											.getId());
		
		this.verificarEmailExistente(vendedorDto.getEmail(), result);
		this.verificarTelefoneExistente(vendedorDto.getTelefone(), result);
		
		if(vendedorDto.getSenha() == null || vendedorDto.getSenha().length() < 5) {
			result.addError(new ObjectError("senhaInvalida", "A senha não pode estar vazia e deve conter mais de 5 caracteres!"));
		}
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(err -> response.addError(err.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Vendedor vendedor = vendedorService.persistir(new Vendedor(vendedorDto));
		historicoService.persistir(new Historico(vendedor.getId()));
		loginService.persistir(new UserLogin(vendedorDto.getNome(), vendedorDto.getEmail(), PasswordEncoder.encode(vendedorDto.getSenha()), false));
		response.setData(new VendedorDTO(vendedor));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("admin/vendedores/vendedor")
	public ResponseEntity<Void> atribuirMaleta(@RequestParam(name = "vendedor") Long vendedorId, 
															@RequestParam(name = "maleta") Long maletaId) {
		
		vendedorService.buscarPorId(vendedorId)
											.orElseThrow(() -> new NotFoundException("Vendedor não encontrado!"));
		
		Maleta maleta = maletaService.buscarPorId(maletaId)
										.orElseThrow(() -> new NotFoundException("Maleta não encontrada!"));
		
		Historico historico = historicoService.buscarPorIdVendedor(vendedorId)
												.orElseThrow(() -> new NotFoundException("Este vendedor não possui histórico!"));
		
		if(maletaAtualService.buscarPorMaleta(maletaId).isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		
		MaletaAtualId id = new MaletaAtualId(vendedorId, maletaId);
		MaletaAtual maletaAtual = new MaletaAtual(id, maleta.getProdutos());
		
		maletaHistoricoService.persistir(new MaletaHistorico(historico.getId(),
											maleta.getId(), maleta.getProdutos()));
		
		maletaAtualService.persistir(maletaAtual);
		return ResponseEntity.accepted().build();

	}
	
	@PutMapping("admin/vendedores/{id}")
	public ResponseEntity<Response<VendedorDTO>> atualizar(@Valid @RequestBody VendedorDTO vendedorDTO, 
													@PathVariable("id") Long id, BindingResult result) {
		
		Response<VendedorDTO> response = new Response<>();
		Vendedor vendedor = vendedorService.buscarPorId(id)
												.orElseThrow(() -> new NotFoundException("Vendedor não encontrado!"));
		
		UserLogin loginVendedor = loginService.buscarPorEmail(vendedor.getEmail()).get();
		
		this.atualizarVendedor(vendedorDTO, vendedor, result, loginVendedor);
		if(result.hasErrors()) {
			result.getAllErrors().forEach(err -> response.addError(err.getDefaultMessage()));
			
			return ResponseEntity.badRequest().body(response);
		}
		
		vendedorService.persistir(vendedor);
		response.setData(new VendedorDTO(vendedor));
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("admin/vendedores")
	public ResponseEntity<Void> retirarMaletaDoVendedor(@RequestParam("maleta") Long idMaleta, 
											@RequestParam("vendedor") Long idVendedor) {
		MaletaAtualId maletaAtualId = new MaletaAtualId(idVendedor, idMaleta);
		maletaAtualService.buscarPorId(maletaAtualId)
													.orElseThrow(() -> new NotFoundException("Essa atribuição não foi feita!"));
		
		maletaAtualService.deletar(maletaAtualId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("admin/vendedores/{id}")
	public ResponseEntity<Void> deletarPorId(@PathVariable("id") Long id) throws NotFoundException {
		Vendedor vendedor = vendedorService.buscarPorId(id).orElseThrow(() -> new NotFoundException("Vendedor não encontrado!"));
		
		Optional<UserLogin> login = loginService.buscarPorEmail(vendedor.getEmail());
		Optional<Historico> historico = historicoService.buscarPorIdVendedor(id);

		maletaAtualService.deletarPorIdVendedor(vendedor.getId());
		maletaHistoricoService.deletarPorIdHistorico(historico.get().getId());
		historicoService.deletarPorId(historico.get().getId());
		loginService.deletarPorId(login.get().getId());
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
	
	private double calculaValorMaletas(Vendedor vendedor) {
		double valorTotal = 0.0;
		List<Maleta> maletas = maletaService.buscarPorIdVendedor(vendedor.getId());
		
		for(Maleta maleta : maletas) {
			for(Produto produto : maleta.getProdutos()) {
				valorTotal = produto.getValor() + valorTotal;
			}
		}
		
		return valorTotal;
	}
}
