package com.caio.pjoias.controllers;

import com.caio.pjoias.dtos.in.VendedorDtoIn;
import com.caio.pjoias.dtos.out.VendedorDtoOut;
import com.caio.pjoias.exceptions.VendedorException;
import com.caio.pjoias.models.Vendedor;
import com.caio.pjoias.services.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("v1/vendedor")
public class VendedorController {
    private final VendedorService vendedorService;

    public VendedorController(final VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @Operation(summary = "Cadastrar vendedor", responses = {
            @ApiResponse(description = "Sucesso", responseCode = "201"),
            @ApiResponse(description = "Falha", responseCode = "400")})
    @PostMapping
    public ResponseEntity<Void> cadastrarNovoVendedor(@Valid @RequestBody VendedorDtoIn vendedorDtoIn) throws VendedorException {
        this.vendedorService.persistirNovo(new Vendedor(vendedorDtoIn));
        return ResponseEntity.created(URI.create("/v1/vendedor")).build();
    }

    @Operation(description = "Listar vendedores", responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200")})
    @GetMapping
    public ResponseEntity<List<VendedorDtoOut>> retornarTodos() {
        var vendedores = this.vendedorService.retornarTodos()
                .stream()
                .map(v -> new VendedorDtoOut(v))
                .collect(toList());

        return ResponseEntity.ok(vendedores);
    }
}
