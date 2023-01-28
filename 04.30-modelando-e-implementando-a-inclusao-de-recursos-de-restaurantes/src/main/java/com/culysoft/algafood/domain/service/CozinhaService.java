package com.culysoft.algafood.domain.service;

import com.culysoft.algafood.domain.exception.EntidadeEmUsoException;
import com.culysoft.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CozinhaService {

    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Cozinha salva(Cozinha cozinha) {
        return cozinhaRepository.salva(cozinha);
    }

    public void remover(Long id) {
       try {
           cozinhaRepository.remover(id);
       } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Nao existe um cadastro de cozinha com id %d", id)
            );
       } catch (DataIntegrityViolationException e) {
           throw new EntidadeEmUsoException(
                   String.format("Cozinha de codio %d nao pode ser removida pois es em uso", id)
           );
       }
    }
}
