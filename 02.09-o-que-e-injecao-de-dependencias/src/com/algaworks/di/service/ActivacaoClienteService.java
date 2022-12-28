package com.algaworks.di.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.Notificador;
import com.algaworks.di.notificacao.NotificadorEmail;

public class ActivacaoClienteService {

    private Notificador notificador;

    public ActivacaoClienteService(Notificador notificador) {
        this.notificador = notificador;
    }

    public void activar(Cliente cliente) {
        cliente.ativar();

        this.notificador.notificar(cliente, "Seu cadastro no sistema esta ativo!");
    }
}
