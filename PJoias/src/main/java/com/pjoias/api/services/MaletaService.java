package com.pjoias.api.services;

import java.util.List;
import java.util.Optional;

import com.pjoias.api.models.entities.Maleta;

public interface MaletaService {
	public Maleta persist(Maleta maleta);
	
	public Optional<Maleta> buscarPorId(Long id);
	
	public List<Maleta> buscarTodos();
	 
	public List<Maleta> buscarPorIdVendedor(Long id);
	
	public Optional<Maleta> buscarPorNome(String nome);
	
	public void deletarPorId(Long id);
}
