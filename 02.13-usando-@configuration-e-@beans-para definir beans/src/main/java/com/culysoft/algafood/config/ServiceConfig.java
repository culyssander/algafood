package com.culysoft.algafood.config;

import com.culysoft.algafood.notificacao.Notificador;
import com.culysoft.algafood.service.AtivacaoClienteServico;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public AtivacaoClienteServico ativacaoClienteServico(Notificador notificador) {
        return new AtivacaoClienteServico(notificador);
    }
}
