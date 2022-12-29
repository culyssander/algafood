package com.culysoft.algafood.config;

import com.culysoft.algafood.notificacao.NotificadorEmail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificacaoConfig {

    @Bean
    public NotificadorEmail notificadorEmail() {
        String smtp = "smtp.culysoft.com";
        NotificadorEmail notificador = new NotificadorEmail(smtp);
        notificador.setCaixaAlta(true);
        return notificador;
    }
}
