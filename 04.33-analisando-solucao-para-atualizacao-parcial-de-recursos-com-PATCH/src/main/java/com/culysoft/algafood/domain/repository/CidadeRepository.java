package com.culysoft.algafood.domain.repository;

import com.culysoft.algafood.domain.model.Cidade;
import com.culysoft.algafood.domain.model.Cozinha;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> listar();
    Cidade buscaPeloId(Long id);

    Cidade salva(Cidade cidade);

    void remover(Long id);
}
