package com.example.gestor_contas.model;

public class Nota {
    private String nome;
    private float valor;

    public Nota(String nome, Float valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Nota() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
