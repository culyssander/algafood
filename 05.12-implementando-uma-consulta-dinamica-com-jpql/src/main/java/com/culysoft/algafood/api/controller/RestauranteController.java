package com.culysoft.algafood.api.controller;

import com.culysoft.algafood.domain.exception.EntidadeEmUsoException;
import com.culysoft.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.RestauranteRepository;
import com.culysoft.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return restauranteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscarPeloId(@PathVariable Long id) {
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);

        if(restauranteOptional.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(restauranteOptional.get());
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
            Optional<Restaurante> restauranteActualOptional = restauranteRepository.findById(restauranteId);

            if(restauranteActualOptional.isPresent()) {
                BeanUtils.copyProperties(restaurante, restauranteActualOptional.get(), "id");

                Restaurante restauranteActual = cadastroRestaurante.salva(restauranteActualOptional.get());

                return ResponseEntity.ok().body(restauranteActual);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> actualizaParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) throws IllegalAccessException {
        Optional<Restaurante> restauranteActualOptional = restauranteRepository.findById(id);

        if (restauranteActualOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        merge(campos, restauranteActualOptional.get());

        return actualiza(id, restauranteActualOptional.get());
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);

        if (restauranteOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Restaurante restaurante = restauranteOptional.get();
        try {
            restauranteRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Restaurante esta associado com a cozinha de id %d em uso", restaurante.getCozinha().getId())
            );
        }
    }

    @GetMapping("por-taxa-frete")
    public List<Restaurante> buscarPorTaxeFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
    }

    @GetMapping("por-nome-e-taxa-frete")
    public List<Restaurante> buscarPorNomeETaxeFrete(String nome,
            BigDecimal taxaInicial, BigDecimal taxaFinal) {
        return restauranteRepository.find(nome, taxaInicial, taxaFinal);
    }

    @GetMapping("por-nome")
    public List<Restaurante> buscarPorNomeAndCozinhaId(String nome, Long cozinhaId) {
        System.out.println("OK: " + nome);
        return restauranteRepository.consultaPorNomeECozinhaId(nome, cozinhaId);
    }

    @GetMapping("primeiro-por-nome")
    public Optional<Restaurante> buscarPrimeiroPorNome(String nome) {
        return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
    }

    @GetMapping("top2-por-nome")
    public List<Restaurante> buscarTop2PorNome(String nome) {
        return restauranteRepository.findTop2RestauranteByNomeContaining(nome);
    }

    @GetMapping("counts")
    public long count(Long cozinhaId) {
        return restauranteRepository.countByCozinhaId(cozinhaId);
    }
}
