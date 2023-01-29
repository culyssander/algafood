package com.culysoft.algafood.jpa;

import com.culysoft.algafood.Application;
import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.CozinhaRepository;
import com.culysoft.algafood.domain.repository.RestauranteRepository;
import com.culysoft.algafood.infrastracture.RestauranteRepositoryImp;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;


public class RestauranteCrudMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);


        RestauranteRepository repository = context.getBean(RestauranteRepository.class);

        System.out.println("List: " + repository.todosRestaurantes());

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("One Piece");
        restaurante.setTaxaFrete(BigDecimal.valueOf(6));

        System.out.println("Cadastro: " + repository.salva(restaurante));

        Restaurante restaurante1 = new Restaurante();
        restaurante1.setId(1L);
        repository.remover(restaurante1);
        System.out.println("List: " + repository.todosRestaurantes());
    }

}
