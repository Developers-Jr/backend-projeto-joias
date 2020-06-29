package com.pjoias.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pjoias.api.models.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	Optional<Endereco> findByIdRevendedor(Long idRevendedor);
}
