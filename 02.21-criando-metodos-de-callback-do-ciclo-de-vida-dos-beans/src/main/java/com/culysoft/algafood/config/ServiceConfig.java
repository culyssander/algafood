package com.culysoft.algafood.config;

import com.culysoft.algafood.service.AtivacaoClienteServico;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean(initMethod = "init", destroyMethod = "preDestroy")
    public AtivacaoClienteServico ativacaoClienteServico() {
        return new AtivacaoClienteServico();
    }
}
