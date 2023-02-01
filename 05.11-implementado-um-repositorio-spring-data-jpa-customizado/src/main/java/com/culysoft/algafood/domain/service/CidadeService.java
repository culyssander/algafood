package com.culysoft.algafood.domain.service;

import com.culysoft.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.culysoft.algafood.domain.model.Cidade;
import com.culysoft.algafood.domain.model.Estado;
import com.culysoft.algafood.domain.repository.CidadeRepository;
import com.culysoft.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    public Cidade salva(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();

        Estado estado = estadoRepository.findById(estadoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Nao existe id %d do estado", estadoId)));

        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);
    }
}
