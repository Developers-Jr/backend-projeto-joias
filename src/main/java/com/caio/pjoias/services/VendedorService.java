package com.caio.pjoias.services;

import com.caio.pjoias.models.Vendedor;
import com.caio.pjoias.repositories.VendedorRepository;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {
    private final VendedorRepository vendedorRepository;

    public VendedorService(final VendedorRepository repository) {
        this.vendedorRepository = repository;
    }

    public Vendedor persistirNovo(Vendedor vendedor) {
        return this.vendedorRepository.save(vendedor);
    }
}
