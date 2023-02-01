package com.culysoft.algafood.api.controller;

import com.culysoft.algafood.domain.model.Cidade;
import com.culysoft.algafood.domain.model.Estado;
import com.culysoft.algafood.domain.repository.CidadeRepository;
import com.culysoft.algafood.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cidades")
public class CidadeController {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> list() {
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscaPeloId(@PathVariable Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);

        if (cidade.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(cidade.get());
    }

    @PostMapping
    public ResponseEntity<?> salva(@RequestBody Cidade cidade) {
        try {
            cidade = cidadeService.salva(cidade);

            return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualiza(@PathVariable Long id, @RequestBody Cidade cidade) {
        Optional<Cidade> cidadeActual = cidadeRepository.findById(id);

        if (cidadeActual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(cidade, cidadeActual, "id");

        try {
            cidadeService.salva(cidade);
            return ResponseEntity.ok(cidadeActual);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Estado> remove(@PathVariable Long id) {
        Optional<Cidade> cidadeActual = cidadeRepository.findById(id);

        if (cidadeActual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            cidadeRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
