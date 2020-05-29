package com.pjoias.api.services;

import java.util.List;
import java.util.Optional;

import com.pjoias.api.models.users.Vendedor;

public interface VendedorService {
	public Optional<Vendedor> buscarPorId(Long id);
	
	public Vendedor persistir(Vendedor vendedor);
	
	public List<Vendedor> buscarTodos();
	
	public Optional<Vendedor> buscarPorTelefone(String telefone);
	
	public void deletarPorId(Long id);
	
	public Optional<Vendedor> buscarPorEmail(String email);
}
