package com.algaworks.di.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.modelo.Produto;
import com.algaworks.di.notificacao.Notificador;
import com.algaworks.di.notificacao.NotificadorEmail;

public class EmissaoNotaFisicalService {

    private Notificador notificador;

    public EmissaoNotaFisicalService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void emitir(Cliente cliente, Produto produto) {
        notificador.notificar(cliente, "nota fiscal do produto " +
                produto.getNome() + " foi emitido!");
    }
}
