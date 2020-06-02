package com.pjoias.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pjoias.api.models.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
