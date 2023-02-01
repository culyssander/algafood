package com.culysoft.algafood.api.controller;

import com.culysoft.algafood.domain.exception.EntidadeEmUsoException;
import com.culysoft.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.culysoft.algafood.domain.service.CozinhaService;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "cozinhas")
public class CozinhaController {

    private CozinhaRepository cozinhaRepository;
    private CozinhaService cozinhaService;

    public CozinhaController(CozinhaRepository cozinhaRepository, CozinhaService cozinhaService) {
        this.cozinhaRepository = cozinhaRepository;
        this.cozinhaService = cozinhaService;
    }
    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
         Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

         if(cozinha.isPresent())
             return ResponseEntity.ok(cozinha.get());

        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cozinha salvar(@RequestBody Cozinha cozinha) {
        return cozinhaService.salva(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> actualizar(@PathVariable Long id, @RequestBody  Cozinha cozinha) {
        Optional<Cozinha> cozinhaActual = cozinhaRepository.findById(id);

        if (cozinhaActual.isEmpty())
            return ResponseEntity.notFound().build();

        BeanUtils.copyProperties(cozinha, cozinhaActual.get(), "id");

        Cozinha cozinhaSalva = cozinhaService.salva(cozinhaActual.get());

        return ResponseEntity.ok(cozinhaSalva);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
        try {
            cozinhaService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
