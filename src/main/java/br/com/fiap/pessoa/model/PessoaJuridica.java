package br.com.fiap.pessoa.model;

import jakarta.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pessoas_juridicas", uniqueConstraints = @UniqueConstraint(columnNames = {"CNPJ"}))
public class PessoaJuridica extends Pessoa {
    @Column(nullable = false, unique = true)
    private String CNPJ;

    @ManyToMany
    @JoinTable(
            name = "pessoa_socio",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "socio_id")
    )
    private Set<Pessoa> socios = new LinkedHashSet<>();
    public String getCNPJ() {
        return CNPJ;
    }
    public PessoaJuridica setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
        return this;
    }

    public Set<Pessoa> getSocios() {
        return Collections.unmodifiableSet(socios);
    }

    public PessoaJuridica addSocio(Pessoa pessoa) {
        if (Objects.nonNull(pessoa)) {
            if (pessoa.equals(this)) throw new RuntimeException("Eu não posso ser o meu próprio sócio");
            this.socios.add(pessoa);
        }
        return this;
    }

    public PessoaJuridica removerSocio(Pessoa pessoa) {
        this.socios.remove(pessoa);
        return this;
    }

    @Override
    public String toString() {
        return "{ " +
                "id=" + id +
                ",  nome='" + nome + '\'' +
                ",  nascimento=" + nascimento + '\'' +
                ",  CNPJ='" + CNPJ + '\'' +
                ",  socios=" + socios +
                " } ";
    }
}
