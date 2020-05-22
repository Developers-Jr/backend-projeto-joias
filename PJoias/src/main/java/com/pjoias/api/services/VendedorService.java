package com.pjoias.api.services;

import java.util.List;
import java.util.Optional;

import com.pjoias.api.models.users.Vendedor;

public interface VendedorService {
	public Optional<Vendedor> findById(Long id);
	
	public Vendedor persist(Vendedor vendedor);
	
	public List<Vendedor> findAllByIdAdmin(Long id);
	
	public void deleteById(Long id);
}
