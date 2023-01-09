package com.culysoft.algafood.infrastracture;

import com.culysoft.algafood.domain.model.Estado;
import com.culysoft.algafood.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Estado> listar() {
        return entityManager.createQuery("FROM Estado", Estado.class)
                .getResultList();
    }
}
