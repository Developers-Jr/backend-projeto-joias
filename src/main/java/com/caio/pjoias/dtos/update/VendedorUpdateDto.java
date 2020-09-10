package com.caio.pjoias.dtos.update;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VendedorUpdateDto {
    @NotNull(message = "Informe o UID do vendedor a ser atualizado")
    @NotEmpty(message = "Informe o UID do vendedor a ser atualizado")
    private String uid;
    private String email;
    private String telefone;

    public boolean verificarEmailValido() {
        var valorEmail = this.email != null ? this.email.replace(" ", "") : null;
        if(valorEmail != "" && valorEmail != null && valorEmail.contains("@")) {
            var posicaoArroba = valorEmail.indexOf("@");
            return posicaoArroba < valorEmail.length() ? true : false;
        }

        return false;
    }

    public boolean verificarTelefoneValido() {
        var valorTelefone = this.telefone != null ? this.telefone.replace(" ", "") : null;
        if(valorTelefone != "" && valorTelefone != null)
            return true;

        return false;
    }
}
