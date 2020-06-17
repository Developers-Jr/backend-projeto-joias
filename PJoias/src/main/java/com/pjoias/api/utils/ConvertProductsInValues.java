package com.pjoias.api.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.pjoias.api.models.entities.Produto;

public class ConvertProductsInValues {
	public static double converterProdutoValor(List<Produto> produtos) {
		double valor = 0.0;
		List<Double> valores = produtos.stream().map(p -> p.getValor()).collect(Collectors.toList());
		
		for(double val : valores) {
			valor += val;
		}
		
		return valor;
	}
}
