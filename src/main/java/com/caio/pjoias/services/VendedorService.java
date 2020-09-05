package com.caio.pjoias.services;

import com.caio.pjoias.exceptions.VendedorException;
import com.caio.pjoias.models.Vendedor;
import com.caio.pjoias.repositories.VendedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Vendedor buscarPor(String uid) throws VendedorException {
        var vendedor = this.vendedorRepository.findById(uid);
        if(vendedor.isPresent()) {
            return vendedor.get();
        }

        throw new VendedorException("Not found!");
    }
}
