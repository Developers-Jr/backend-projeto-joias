package com.pjoias.api.repositories;

import static com.pjoias.api.builders.AdminBuilder.umAdmin;
import static com.pjoias.api.builders.MaletaBuilder.umaMaleta;
import static com.pjoias.api.builders.VendedorBuilder.umVendedor;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.pjoias.api.models.entities.Maleta;
import com.pjoias.api.models.users.Admin;
import com.pjoias.api.models.users.Vendedor;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class MaletaRepositoryTest {
	
	@Autowired
	private MaletaRepository maletaRepository;
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	private Admin admin;
	private Vendedor vendedor;
	private Maleta maleta;
	
	@BeforeAll
	public void setUp() {
		this.admin = this.adminRepository.save(umAdmin().agora());
		this.vendedor = this.vendedorRepository.save(umVendedor(this.admin).agora());
		this.maleta = this.maletaRepository.save(umaMaleta().agora());
	}
	
	@Test
	public void deveBuscarMaletasAtribuidasDeUmVendedor() {
		//cenario
		
		//acao
		
		//verificacao
		Assertions.fail();
	}
}
