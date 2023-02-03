package com.culysoft.algafood.infrastructure.repository.spec;

import com.culysoft.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpec {

    public static Specification<Restaurante> taxaFreteGratis() {
        return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO) ;
    }

    public static Specification<Restaurante> nomeSemelhantes(String nome) {
        return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
    }
}
