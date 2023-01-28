package com.culysoft.algafood.api.controller;

import com.culysoft.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.RestauranteRepository;
import com.culysoft.algafood.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("restaurantes")
@RestController
public class RestauranteController {

    private RestauranteRepository restauranteRepository;
    private RestauranteService cadastroRestaurante;

    public RestauranteController(RestauranteRepository restauranteRepository,
                                 RestauranteService cadastroRestaurante) {
        this.restauranteRepository = restauranteRepository;
        this.cadastroRestaurante = cadastroRestaurante;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> salva(@RequestBody Restaurante restaurante) {
        try {
            restaurante = cadastroRestaurante.salva(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> actualiza(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
        try {
            Restaurante restauranteActual = restauranteRepository.buscaPeloId(restauranteId);

            if(restauranteActual != null) {
                BeanUtils.copyProperties(restaurante, restauranteActual, "id");

                restauranteActual = cadastroRestaurante.salva(restauranteActual);

                return ResponseEntity.ok().body(restauranteActual);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
