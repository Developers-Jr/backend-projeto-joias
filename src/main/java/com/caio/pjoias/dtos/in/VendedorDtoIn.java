package com.caio.pjoias.dtos.in;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class VendedorDtoIn implements Serializable {
    private String uid;

    @NotEmpty(message = "Informe um nome")
    private String nome;

    @NotEmpty(message = "Informe um sobrenome!")
    private String sobrenome;

    @NotEmpty(message = "O email é obrigatório")
    @Email(message = "Informe um email válido")
    private String email;

    @NotEmpty(message = "A senha é obrigatória")
    @Size(min = 8, message = "A senha deve conter ao menos 8 caractéres")
    private String senha;

    @NotEmpty(message = "O telefone é obrigatório")
    private String telefone;
}
