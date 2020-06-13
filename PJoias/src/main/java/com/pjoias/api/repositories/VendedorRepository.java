package com.pjoias.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pjoias.api.models.users.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{
	List<Vendedor> findByIdAdmin(Long id);
	
	Optional<Vendedor> findByEmail(String email);
	
	Optional<Vendedor> findByTelefone(String telefone);
}
