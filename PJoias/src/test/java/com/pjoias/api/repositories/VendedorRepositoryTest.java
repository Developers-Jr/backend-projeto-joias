package com.pjoias.api.repositories;

import static com.pjoias.api.builders.AdminBuilder.umAdmin;
import static com.pjoias.api.builders.VendedorBuilder.umVendedor;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.pjoias.api.models.users.Admin;
import com.pjoias.api.models.users.Vendedor;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
public class VendedorRepositoryTest {
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Test
	public void buscarPorIdAdmin() {
		//cenario
		Admin admin = adminRepository.save(umAdmin().agora());
		Vendedor vendedor = vendedorRepository.save(umVendedor(admin).agora());
		
		//acao
		List<Vendedor> result = vendedorRepository.findByIdAdmin(admin.getId());
		
		
		//verificacao
		assertThat(result.get(0)).isEqualTo(vendedor);
	}
}
