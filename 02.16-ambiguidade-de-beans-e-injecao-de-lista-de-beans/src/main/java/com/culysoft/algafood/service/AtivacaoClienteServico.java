package com.culysoft.algafood.service;

import com.culysoft.algafood.modelo.Cliente;
import com.culysoft.algafood.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteServico {

    @Autowired
    private List<Notificador> notificadores;

    public void ativar(Cliente cliente, String mensagem) {
        cliente.ativar();

        for (Notificador notificador : notificadores) {
            notificador.notificar(cliente, mensagem);
        }
    }

}
