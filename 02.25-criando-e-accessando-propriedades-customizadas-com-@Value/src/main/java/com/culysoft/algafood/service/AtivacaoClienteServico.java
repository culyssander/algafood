package com.culysoft.algafood.service;

import com.culysoft.algafood.modelo.Cliente;
import com.culysoft.algafood.notificacao.NivelNotificacao;
import com.culysoft.algafood.notificacao.Notificador;
import com.culysoft.algafood.notificacao.TipoNotificadocao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
public class AtivacaoClienteServico {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void ativar(Cliente cliente) {
        cliente.ativar();

        eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
    }

}
