package com.caio.pjoias.controllers;

import com.caio.pjoias.dtos.VendedorDto;
import com.caio.pjoias.models.Vendedor;
import com.caio.pjoias.services.VendedorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/vendedor")
public class VendedorController {
    private final VendedorService vendedorService;

    public VendedorController(final VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @PostMapping
    public HttpStatus cadastrarNovoVendedor(@Valid @RequestBody VendedorDto vendedorDto) {
        var resultado = this.vendedorService.persistirNovo(new Vendedor(vendedorDto));
        System.out.println(resultado.toString());
        return resultado != null ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }
}
