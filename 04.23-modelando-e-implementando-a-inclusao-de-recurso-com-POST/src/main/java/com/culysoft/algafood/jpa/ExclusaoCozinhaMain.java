package com.culysoft.algafood.jpa;

import com.culysoft.algafood.Application;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ExclusaoCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository cadastroCozinha = context.getBean(CozinhaRepository.class);

        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);

        cadastroCozinha.remover(cozinha);

        cadastroCozinha.todasCozinhas().forEach(System.out::println);
    }
}
