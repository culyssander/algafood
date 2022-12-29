package com.culysoft.algafood.modelo;

public class Cliente {
    private String nome;
    private String email;
    private String telefone;
    private boolean ativo;

    public Cliente(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public void ativar() {
        this.ativo = true;
    }
}
