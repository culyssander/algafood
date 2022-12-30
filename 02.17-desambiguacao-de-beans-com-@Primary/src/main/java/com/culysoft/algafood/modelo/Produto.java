package com.culysoft.algafood.modelo;

public class Produto {

    private String nomeDoProduto;
    private String valorTotal;

    public Produto(String nomeDoProduto, String valorTotal) {
        this.nomeDoProduto = nomeDoProduto;
        this.valorTotal = valorTotal;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public String getValorTotal() {
        return valorTotal;
    }
}
