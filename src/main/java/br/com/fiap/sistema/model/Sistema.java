package br.com.fiap.sistema.model;

import br.com.fiap.pessoa.model.Pessoa;
import br.com.fiap.pessoa.model.PessoaJuridica;
import jakarta.persistence.*;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "sistemas", uniqueConstraints = @UniqueConstraint(columnNames = {"sigla"}))
public class Sistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String sigla;

    @ManyToMany
    @JoinTable(
            name = "sistema_responsavel",
            joinColumns = @JoinColumn(name = "sistema_id"),
            inverseJoinColumns = @JoinColumn(name = "pessoa_id")
    )
    private Set<Pessoa> responsaveis = new LinkedHashSet<>();

    public Sistema(String nome, String sigla) {
        this.nome = nome;
        this.sigla = sigla;
    }

    public Sistema(Long id, String nome, String sigla, Set<Pessoa> responsaveis) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
        this.responsaveis = responsaveis;
    }

    public Sistema addResponsavel(PessoaJuridica pessoa) {
        this.responsaveis.add(pessoa);
        return this;
    }

    public Sistema removeResponsavel(Pessoa pessoa) {
        this.responsaveis.remove(pessoa);
        return this;
    }

    public Set<Pessoa> getResponsaveis() {
        return Collections.unmodifiableSet(this.responsaveis);
    }

    public Long getId() {
        return id;
    }

    public Sistema setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Sistema setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getSigla() {
        return sigla;
    }

    public Sistema setSigla(String sigla) {
        this.sigla = sigla;
        return this;
    }

    @Override
    public String toString() {
        return "Sistema{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", responsaveis=" + responsaveis +
                '}';
    }
}
