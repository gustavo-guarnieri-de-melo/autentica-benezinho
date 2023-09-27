package br.com.fiap.pessoa.model;

import jakarta.persistence.*;

public enum Sexo {

    @Enumerated(EnumType.STRING) // Adicione esta anotação
    FEMININO(0, "Feminino", 'F'),

    @Enumerated(EnumType.STRING) // Adicione esta anotação
    MASCULINO(1, "Masculino", 'M');

    private int id;
    private String nome;
    private Character sigla;

    Sexo(int id, String nome, Character sigla) {
        this.id = id;
        this.nome = nome;
        this.sigla = sigla;
    }

    public int getId() {
        return id;
    }

    public Sexo setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Sexo setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public Character getSigla() {
        return sigla;
    }

    public Sexo setSigla(Character sigla) {
        this.sigla = sigla;
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(sigla);
    }
}
