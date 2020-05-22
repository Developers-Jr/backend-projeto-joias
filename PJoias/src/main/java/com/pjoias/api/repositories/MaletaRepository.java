package com.pjoias.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pjoias.api.models.entities.Maleta;

public interface MaletaRepository extends JpaRepository<Maleta, Long>{
	Optional<Maleta> findByNome(String name);

}
