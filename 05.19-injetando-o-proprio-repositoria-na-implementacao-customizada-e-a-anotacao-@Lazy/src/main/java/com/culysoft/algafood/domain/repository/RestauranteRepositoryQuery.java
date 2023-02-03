package com.culysoft.algafood.domain.repository;

import com.culysoft.algafood.domain.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryQuery {

    public List<Restaurante> find(String nome, BigDecimal taxaInical,
                                  BigDecimal taxaFinal);

    List<Restaurante> findComFreteGratis(String nome);
}
