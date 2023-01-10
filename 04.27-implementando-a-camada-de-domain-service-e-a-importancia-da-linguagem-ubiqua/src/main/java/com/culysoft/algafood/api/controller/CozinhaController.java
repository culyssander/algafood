package com.culysoft.algafood.api.controller;

import com.culysoft.algafood.api.model.CozinhasXmlWrapper;
import com.culysoft.algafood.domain.CozinhaService;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.repository.CozinhaRepository;
import lombok.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.List;

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
        return cozinhaService.salva(cozinha);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> actualizar(@PathVariable Long id, @RequestBody  Cozinha cozinha) {
        Cozinha cozinhaActual = cozinhaRepository.buscaPeloId(id);

        if (cozinha == null)
            return ResponseEntity.notFound().build();

//        cozinhaActual.setNome(cozinha.getNome());
        BeanUtils.copyProperties(cozinha, cozinhaActual, "id");

        cozinhaRepository.salva(cozinhaActual);

        return ResponseEntity.ok(cozinhaActual);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
        try {
            Cozinha cozinha = cozinhaRepository.buscaPeloId(id);

            if (cozinha == null)
                return ResponseEntity.notFound().build();

            cozinhaRepository.remover(cozinha);

            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
