package com.caio.pjoias.models;

import com.caio.pjoias.dtos.in.VendedorDtoIn;
import com.caio.pjoias.utils.PasswordUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Table(name = "vendedor")
@Entity
public class Vendedor implements Serializable {
    @Id
    @Column(name = "uid")
    private String uid;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobrenome")
    private String sobrenome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @Column(name = "telefone")
    private String telefone;

    @Transient
    private GeraUid geraUid = () -> {
        return UUID.randomUUID().toString();
    };

    public Vendedor() {}

    public Vendedor(VendedorDtoIn dto) {
        this.uid = dto.getUid() != null ? dto.getUid() : geraUid.gerarUid();
        this.nome = dto.getNome();
        this.sobrenome = dto.getSobrenome();
        this.email = dto.getEmail();
        this.senha = PasswordUtils.encode(dto.getSenha());
        this.telefone = dto.getTelefone();
    }

    public void setSenha(String senha) {
        this.senha = PasswordUtils.encode(senha);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendedor vendedor = (Vendedor) o;
        return Objects.equals(nome, vendedor.nome) &&
                Objects.equals(sobrenome, vendedor.sobrenome) &&
                Objects.equals(email, vendedor.email) &&
                Objects.equals(senha, vendedor.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobrenome, email, senha);
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "uid='"+ uid + '\'' +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}

@FunctionalInterface
interface GeraUid {
    String gerarUid();
}
