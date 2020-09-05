package com.caio.pjoias.controllers;

import com.caio.pjoias.dtos.in.VendedorDtoIn;
import com.caio.pjoias.dtos.out.VendedorDtoOut;
import com.caio.pjoias.exceptions.VendedorException;
import com.caio.pjoias.models.Vendedor;
import com.caio.pjoias.services.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
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
    public ResponseEntity<List<EntityModel<VendedorDtoOut>>> retornarTodos() {
        var vendedores =
                this.vendedorService.retornarTodos()
                .stream()
                .map(v -> {
                    var model = EntityModel.of(new VendedorDtoOut(v));
                    return model.add(Link.of("https://localhost/v1/vendedor/" + model.getContent().getUid()));
                })
                .collect(toList());

        return ResponseEntity.ok(vendedores);
    }
}
