package com.caio.pjoias.services;

import com.caio.pjoias.exceptions.VendedorException;
import com.caio.pjoias.models.Vendedor;
import com.caio.pjoias.repositories.VendedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class VendedorService {
    private final VendedorRepository vendedorRepository;

    public VendedorService(final VendedorRepository repository) {
        this.vendedorRepository = repository;
    }

    public Vendedor persistirNovo(Vendedor vendedor) throws VendedorException {
        if (this.vendedorRepository.findById(vendedor.getUid()).isPresent()) {
            throw new VendedorException("Tente novamente!");
        }

        return this.vendedorRepository.save(vendedor);
    }

    public List<Vendedor> retornarTodos() {
        return this.vendedorRepository.findAll();
    }
}
