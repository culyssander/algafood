package com.culysoft.algafood.api.controller;

import com.culysoft.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.RestauranteRepository;
import com.culysoft.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @PatchMapping("{id}")
    public ResponseEntity<?> actualizaParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) throws IllegalAccessException {
        Restaurante restauranteActual = restauranteRepository.buscaPeloId(id);

        if (restauranteActual == null) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteActual);

        return actualiza(id, restauranteActual);
    }

    private void merge(Map<String, Object> campo, Restaurante restaurante) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restaranteOrigem = objectMapper.convertValue(campo, Restaurante.class);

        campo.forEach((index, value) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, index);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restaranteOrigem);
            System.out.println(index + " "+value + " " + field.getName() + " " + novoValor);

            ReflectionUtils.setField(field, restaurante, novoValor);
        });
    }
}
