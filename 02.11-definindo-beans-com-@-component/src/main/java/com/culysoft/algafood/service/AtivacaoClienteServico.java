package com.culysoft.algafood.service;

import com.culysoft.algafood.modelo.Cliente;
import com.culysoft.algafood.notificacao.NotificadorEmail;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteServico {
    private NotificadorEmail notificador;

    public void ativar(Cliente cliente, String mensagem) {
        cliente.ativar();

        this.notificador.notificar(cliente, mensagem);
    }
}
