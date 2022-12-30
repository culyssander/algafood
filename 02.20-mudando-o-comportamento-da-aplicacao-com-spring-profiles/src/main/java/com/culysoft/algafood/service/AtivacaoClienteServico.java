package com.culysoft.algafood.service;

import com.culysoft.algafood.modelo.Cliente;
import com.culysoft.algafood.notificacao.NivelNotificacao;
import com.culysoft.algafood.notificacao.Notificador;
import com.culysoft.algafood.notificacao.TipoNotificadocao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AtivacaoClienteServico {

    @TipoNotificadocao(NivelNotificacao.NORMAL)
    @Autowired
    private Notificador notificador;

    public void ativar(Cliente cliente) {
        cliente.ativar();
        notificador.notificar(cliente, "ativado");
    }

}
