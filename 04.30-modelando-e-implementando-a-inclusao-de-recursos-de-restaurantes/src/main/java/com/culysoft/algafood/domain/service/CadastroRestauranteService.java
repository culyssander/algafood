package com.culysoft.algafood.domain.service;

import com.culysoft.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.CozinhaRepository;
import com.culysoft.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salva(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();

        Cozinha cozinha = cozinhaRepository.buscaPeloId(cozinhaId);

        if (cozinha == null)
            throw new EntidadeNaoEncontradaException(
                    String.format("Nao existe cadastro de cozinha com codigo %d", cozinhaId));

        restaurante.setCozinha(cozinha);
        return restauranteRepository.salva(restaurante);
    }
}
