package com.caio.pjoias.dtos.out;

import com.caio.pjoias.models.Vendedor;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class VendedorDtoOut implements Serializable {
    private String uid;
    private String nome;
    private String sobrenome;
    private String email;

    public VendedorDtoOut() {}

    public VendedorDtoOut(Vendedor vendedor) {
        this.uid = vendedor.getUid();
        this.nome = vendedor.getNome();
        this.sobrenome = vendedor.getSobrenome();
        this.email = vendedor.getEmail();
    }
}
