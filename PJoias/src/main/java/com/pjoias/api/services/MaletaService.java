package com.pjoias.api.services;

import java.util.List;
import java.util.Optional;

import com.pjoias.api.models.entities.Maleta;

public interface MaletaService {
	public Maleta persist(Maleta maleta);
	
	public Optional<Maleta> findById(Long id);
	
	public List<Maleta> findAll();
	
	public Optional<Maleta> findByName(String nome);
}
