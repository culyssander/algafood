package com.culysoft.algafood.api.controller;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("testes")
public class TestController {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @GetMapping("por-nome")
    public List<Cozinha> lista(String nome) {
        return cozinhaRepository.findByNome(nome);
    }
}