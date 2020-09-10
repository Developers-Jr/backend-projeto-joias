package com.caio.pjoias.dtos.output;

import com.caio.pjoias.models.Vendedor;
import lombok.Getter;
import java.io.Serializable;

@Getter
public class VendedorOutputDto implements Serializable {
    private String uid;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;

    public VendedorOutputDto() {}

    public VendedorOutputDto(Vendedor vendedor) {
        this.uid = vendedor.getUid();
        this.nome = vendedor.getNome();
        this.sobrenome = vendedor.getSobrenome();
        this.email = vendedor.getEmail();
        this.telefone = vendedor.getTelefone();
    }
}
