package com.culysoft.algafood.domain.service;

import com.culysoft.algafood.domain.model.Estado;
import com.culysoft.algafood.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public Estado salva(Estado estado) {
        return estadoRepository.save(estado);
    }

    public void remove(Long id) {
        estadoRepository.deleteById(id);
    }
}
