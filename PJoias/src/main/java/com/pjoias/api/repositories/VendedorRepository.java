package com.pjoias.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pjoias.api.models.users.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{
	List<Vendedor> findByIdAdmin(Long id);
}
