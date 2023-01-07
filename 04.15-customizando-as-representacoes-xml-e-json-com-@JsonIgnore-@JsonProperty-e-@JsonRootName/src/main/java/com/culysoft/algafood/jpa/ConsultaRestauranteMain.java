package com.culysoft.algafood.jpa;

import com.culysoft.algafood.Application;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

public class ConsultaRestauranteMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestauranteRepository repository = context.getBean(RestauranteRepository.class);

        System.out.println(repository.todosRestaurantes());

    }
}
