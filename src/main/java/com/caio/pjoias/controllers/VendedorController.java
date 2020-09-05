package com.caio.pjoias.controllers;

import com.caio.pjoias.dtos.in.VendedorDtoIn;
import com.caio.pjoias.dtos.out.VendedorDtoOut;
import com.caio.pjoias.exceptions.VendedorException;
import com.caio.pjoias.models.Vendedor;
import com.caio.pjoias.services.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("v1/vendedor")
public class VendedorController {
    private final VendedorService vendedorService;

    private LinkHateoas<VendedorDtoOut> hateoas = vendedor -> {
        var link = linkTo(VendedorDtoOut.class).slash(vendedor.getUid()).withSelfRel();
        var model = EntityModel.of(new VendedorDtoOut(vendedor));
        return model.add(link);
    };

    public VendedorController(final VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @Operation(summary = "Cadastrar vendedor", responses = {
            @ApiResponse(description = "Sucesso", responseCode = "201"),
            @ApiResponse(description = "Falha", responseCode = "400")})
    @PostMapping
    public ResponseEntity<EntityModel<Void>> cadastrarNovoVendedor(@Valid @RequestBody VendedorDtoIn vendedorDtoIn) throws VendedorException {
        this.vendedorService.persistirNovo(new Vendedor(vendedorDtoIn));
        return ResponseEntity.created(URI.create("/v1/vendedor")).build();
    }

    @Operation(summary = "Listar vendedores", responses = {
            @ApiResponse(description = "Sucesso", responseCode = "200")})
    @GetMapping
    public ResponseEntity<List<EntityModel<VendedorDtoOut>>> retornarTodos() {
        var vendedores =
                this.vendedorService.retornarTodos()
                .stream()
                .map(vendedor -> hateoas.gerarLink(vendedor))
                .collect(toList());

        return ResponseEntity.ok(vendedores);
    }

    @GetMapping("{id}")
    public ResponseEntity<EntityModel<VendedorDtoOut>> buscarPor(@RequestParam("id") String uid) throws VendedorException {
        var vendedor = this.vendedorService.buscarPor(uid);
        return null;
    }
}

@FunctionalInterface
interface LinkHateoas<T> {
    EntityModel<T> gerarLink(Vendedor vendedor);
}
