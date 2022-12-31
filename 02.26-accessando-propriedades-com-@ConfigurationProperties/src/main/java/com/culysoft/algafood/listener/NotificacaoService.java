package com.culysoft.algafood.listener;

import com.culysoft.algafood.notificacao.Notificador;
import com.culysoft.algafood.notificacao.TipoNotificadocao;
import com.culysoft.algafood.service.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoService {

    @TipoNotificadocao
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
//        System.out.println("Cliente " + event.getCliente().getNome() + " agora esta ativo.");
        notificador.notificar(event.getCliente(), "agora esta ativo.");
    }

}
