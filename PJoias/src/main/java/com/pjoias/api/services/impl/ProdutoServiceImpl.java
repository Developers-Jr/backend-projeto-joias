package com.pjoias.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pjoias.api.models.entities.Produto;
import com.pjoias.api.repositories.ProdutoRepository;
import com.pjoias.api.services.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public Produto persistir(Produto produto) {
		return produtoRepository.save(produto);
	}

	@Override
	public void excluirPorId(Long id) {
		produtoRepository.deleteById(id);
	}

	@Override
	public Optional<Produto> buscarPorId(Long id) {
		return produtoRepository.findById(id);
	}

}
