package com.caio.pjoias.models;

import com.caio.pjoias.dtos.VendedorDto;
import com.caio.pjoias.utils.PasswordUtils;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Table(name = "vendedor")
@Entity
public class Vendedor {
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

    public Vendedor() {}

    public Vendedor(String uid, String nome, String sobrenome, String email, String senha) {
        this.uid = uid;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = PasswordUtils.encode(senha);
    }

    public Vendedor(VendedorDto dto) {
        this.uid = dto.getUid();
        this.nome = dto.getNome();
        this.sobrenome = dto.getSobrenome();
        this.email = dto.getEmail();
        this.senha = PasswordUtils.encode(dto.getSenha());
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
