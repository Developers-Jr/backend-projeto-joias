package com.caio.pjoias.dtos.in;
import com.caio.pjoias.models.Vendedor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class VendedorDtoIn {
    @NotEmpty(message = "Uid não pode estar vazio")
    private String uid;

    @NotEmpty(message = "O nome não pode estar vazio")
    private String nome;

    @NotEmpty(message = "O sobrenome não pode estar vazio")
    private String sobrenome;

    @NotEmpty(message = "O email não pode estar vazio")
    @Email(message = "Informe um email válido")
    private String email;

    @NotEmpty(message = "A senha não pode estar vazia")
    @Size(min = 8, message = "A senha deve conter ao menos 8 caractéres")
    private String senha;
}
