package com.culysoft.algafood.domain.repository;

import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> todosRestaurantes();
    Restaurante buscaPeloId(Long id);

    Restaurante salva(Restaurante restaurante);

    void remover(Long id);
}
