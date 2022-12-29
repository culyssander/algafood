package com.culysoft.algafood.service;

import com.culysoft.algafood.modelo.Cliente;
import com.culysoft.algafood.notificacao.Notificador;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteServico {
    private Notificador notificador;

    public AtivacaoClienteServico(Notificador notificador) {
        this.notificador = notificador;
        System.out.println("AtivacaoClienteServico: " + notificador);
    }

    public void ativar(Cliente cliente, String mensagem) {
        cliente.ativar();

        this.notificador.notificar(cliente, mensagem);
    }
}
