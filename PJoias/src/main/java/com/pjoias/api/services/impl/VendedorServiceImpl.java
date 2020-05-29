package com.pjoias.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjoias.api.models.users.Vendedor;
import com.pjoias.api.repositories.VendedorRepository;
import com.pjoias.api.services.VendedorService;

@Service
public class VendedorServiceImpl implements VendedorService {
	
	@Autowired
	private VendedorRepository vendedorRepository;

	@Override
	public Optional<Vendedor> buscarPorId(Long id) {
		return vendedorRepository.findById(id);
	}

	@Override
	public Vendedor persistir(Vendedor vendedor) {
		return vendedorRepository.save(vendedor);
	}

	@Override
	public List<Vendedor> buscarTodos() {
		return vendedorRepository.findAll();
	}

	@Override
	public void deletarPorId(Long id) {
		vendedorRepository.deleteById(id);
	}

	@Override
	public Optional<Vendedor> buscarPorEmail(String email) {
		return vendedorRepository.findByEmail(email);
	}

	@Override
	public Optional<Vendedor> buscarPorTelefone(String telefone) {
		return vendedorRepository.findByTelefone(telefone);
	}
}
