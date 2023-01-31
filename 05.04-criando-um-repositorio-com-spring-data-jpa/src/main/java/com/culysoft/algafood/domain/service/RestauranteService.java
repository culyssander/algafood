package com.culysoft.algafood.domain.service;

import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.CozinhaRepository;
import com.culysoft.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salva(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow();

        restaurante.setCozinha(cozinha);
        return restauranteRepository.salva(restaurante);
    }

}
