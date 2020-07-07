package com.pjoias.api.repositories;

import static com.pjoias.api.builders.AdminBuilder.umAdmin;
import static com.pjoias.api.builders.RevendedorBuilder.umRevendedor;
import static com.pjoias.api.builders.VendedorBuilder.umVendedor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.pjoias.api.models.entities.Revendedor;
import com.pjoias.api.models.users.Admin;
import com.pjoias.api.models.users.Vendedor;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class RevendedorRepositoryTest {
	
	@Autowired
	private RevendedorRepository revendedorRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	private Vendedor vendedor;
	private Admin admin;
	
	@BeforeAll
	public void setUp() {
		this.admin = this.adminRepository.save(umAdmin().agora());
		this.vendedor = this.vendedorRepository.save(umVendedor(admin).agora());
	}
	
	@Test
	public void deveBuscarPeloIdDoVendedorResponsavel() {
		//cenario 
		this.revendedorRepository.save(umRevendedor().comVendedor(this.vendedor).agora());
		this.revendedorRepository.save(umRevendedor().comVendedor(this.vendedor).agora());
		this.revendedorRepository.save(umRevendedor().comVendedor(this.vendedor).agora());
		
		//acao
		List<Revendedor> result = this.revendedorRepository.findByVendedorId(this.vendedor.getId());
		
		//verificacao
		assertAll("revendedor",
			() -> assertThat(result.get(0).getIdVendedor()).isEqualTo(this.vendedor.getId()),
			() -> assertThat(result.get(1).getIdVendedor()).isEqualTo(this.vendedor.getId()),
			() -> assertThat(result.get(2).getIdVendedor()).isEqualTo(this.vendedor.getId())
		);
	}
}
