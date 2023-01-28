package com.culysoft.algafood.domain.repository;

import com.culysoft.algafood.domain.model.Estado;

import java.util.List;

public interface EstadoRepository {

    List<Estado> listar();
    Estado buscaPeloId(Long id);

    Estado salva(Estado estado);

    void remover(Long id);
}
