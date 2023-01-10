package com.culysoft.algafood.api.controller;

import com.culysoft.algafood.api.model.CozinhasXmlWrapper;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.repository.CozinhaRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "cozinhas")
public class CozinhaController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.todasCozinhas();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhasXmlWrapper listarXml() {
        return new CozinhasXmlWrapper(cozinhaRepository.todasCozinhas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
         Cozinha cozinha = cozinhaRepository.buscaPeloId(id);

         if(cozinha != null)
             return ResponseEntity.ok(cozinha);

         return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cozinha salvar(@RequestBody Cozinha cozinha) {
        return cozinhaRepository.salva(cozinha);
    }
}
