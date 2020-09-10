package com.caio.pjoias.services;

import com.caio.pjoias.dtos.update.VendedorUpdateDto;
import com.caio.pjoias.exceptions.VendedorException;
import com.caio.pjoias.models.Vendedor;
import com.caio.pjoias.repositories.VendedorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Vendedor atualizar(VendedorUpdateDto updateVendedor) throws VendedorException {
        Vendedor vendedor = this
                .vendedorRepository
                .findById(updateVendedor.getUid())
                .orElseThrow(() -> new VendedorException("Vendedor inexistente!"));

        if(updateVendedor.verificarEmailValido()) {
            vendedor.setEmail(updateVendedor.getEmail());
        }

        if(updateVendedor.verificarTelefoneValido()) {
            vendedor.setTelefone(updateVendedor.getTelefone());
        }

        return this.vendedorRepository.save(vendedor);
    }
}
