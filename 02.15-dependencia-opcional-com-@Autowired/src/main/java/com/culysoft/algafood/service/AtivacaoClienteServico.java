package com.culysoft.algafood.service;

import com.culysoft.algafood.modelo.Cliente;
import com.culysoft.algafood.notificacao.Notificador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtivacaoClienteServico {

    @Autowired(required = false)
    private Notificador notificador;

    public void ativar(Cliente cliente, String mensagem) {
        cliente.ativar();

        if(notificador != null)
            this.notificador.notificar(cliente, mensagem);
        else
            System.out.println("Nao existe notificador, mas cliente foi ativado");
    }

}
