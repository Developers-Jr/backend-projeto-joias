package com.caio.pjoias.controllers;

import com.caio.pjoias.dtos.in.VendedorDtoIn;
import com.caio.pjoias.dtos.out.VendedorDtoOut;
import com.caio.pjoias.exceptions.VendedorException;
import com.caio.pjoias.models.Vendedor;
import com.caio.pjoias.services.VendedorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("v1/vendedor")
public class VendedorController {
    private final VendedorService vendedorService;

    public VendedorController(final VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping
    public HttpStatus cadastrarNovoVendedor(@Valid @RequestBody VendedorDtoIn vendedorDtoIn) throws VendedorException {
        this.vendedorService.persistirNovo(new Vendedor(vendedorDtoIn));
        return HttpStatus.CREATED;
    }

    @GetMapping
    public ResponseEntity<List<VendedorDtoOut>> retornarTodos() {
        var vendedores = this.vendedorService.retornarTodos()
                .stream()
                .map(v -> new VendedorDtoOut(v))
                .collect(toList());

        return ResponseEntity.ok(vendedores);
    }
}
