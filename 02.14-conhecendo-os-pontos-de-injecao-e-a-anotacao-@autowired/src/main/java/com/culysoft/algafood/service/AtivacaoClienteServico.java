package com.culysoft.algafood.service;

import com.culysoft.algafood.modelo.Cliente;
import com.culysoft.algafood.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteServico {

    @Autowired
    private Notificador notificador;

//    @Autowired
//    public AtivacaoClienteServico(Notificador notificador) {
//        this.notificador = notificador;
//    }

//    public AtivacaoClienteServico(String qualquer) {
//
//    }

    public void ativar(Cliente cliente, String mensagem) {
        cliente.ativar();

        this.notificador.notificar(cliente, mensagem);
    }

//    @Autowired
//    public void setNotificador(Notificador notificador) {
//        this.notificador = notificador;
//    }
}
