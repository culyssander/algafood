package com.culysoft.algafood.jpa;

import com.culysoft.algafood.Application;
import com.culysoft.algafood.domain.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class BuscaCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CadastroCozinha cadastroCozinha = context.getBean(CadastroCozinha.class);

        System.out.println("BUSCA: " + cadastroCozinha.buscarPeloId(1L));
    }
}
