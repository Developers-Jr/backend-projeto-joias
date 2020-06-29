package com.pjoias.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pjoias.api.models.entities.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	void deleteByIdRevendedor(Long id);
}
