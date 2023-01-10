package com.culysoft.algafood.api.controller;

import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.RestauranteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("restaurantes")
@RestController
public class RestauranteController {

    private RestauranteRepository restauranteRepository;

    public RestauranteController(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @GetMapping
    public List<Restaurante> listar() {
        return restauranteRepository.todosRestaurantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPeloId(@PathVariable Long id) {
        Restaurante restaurante = restauranteRepository.buscaPeloId(id);

        if(restaurante == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(restaurante);
    }
}
