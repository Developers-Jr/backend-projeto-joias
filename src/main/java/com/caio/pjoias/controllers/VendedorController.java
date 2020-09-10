package com.caio.pjoias.controllers;

import com.caio.pjoias.dtos.input.VendedorInputDto;
import com.caio.pjoias.dtos.output.VendedorOutputDto;
import com.caio.pjoias.dtos.update.VendedorUpdateDto;
import com.caio.pjoias.exceptions.VendedorException;
import com.caio.pjoias.models.Vendedor;
import com.caio.pjoias.services.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    public ResponseEntity<Void> cadastrarNovoVendedor(@Valid @RequestBody VendedorInputDto vendedorInputDto) throws VendedorException {
        this.vendedorService.persistirNovo(new Vendedor(vendedorInputDto));
        return ResponseEntity.created(URI.create("/v1/vendedor")).build();
    }

    @Operation(summary = "Atualizar vendedor", responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200"),
            @ApiResponse(description = "Falha", responseCode = "400")
    })
    @PutMapping
    public ResponseEntity<Void> atualizarDadosVendedor(@Valid @RequestBody VendedorUpdateDto updateVendedor) throws VendedorException {
        this.vendedorService.atualizar(updateVendedor);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Listar vendedores", responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200")})
    @GetMapping
    public ResponseEntity<List<VendedorOutputDto>> retornarTodos() {
        var vendedores =
                this.vendedorService.retornarTodos()
                .stream()
                .map(VendedorOutputDto::new)
                .collect(toList());

        return ResponseEntity.ok(vendedores);
    }

    @GetMapping("{id}")
    public ResponseEntity<VendedorOutputDto> buscarPor(@PathVariable("id") String uid) throws VendedorException {
        var vendedor = this.vendedorService.buscarPor(uid);
        return ResponseEntity.ok(new VendedorOutputDto(vendedor));
    }
}

