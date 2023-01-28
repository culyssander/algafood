package com.culysoft.algafood.infrastracture;

import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.model.Estado;
import com.culysoft.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public Estado buscaPeloId(Long id) {
        return entityManager.find(Estado.class, id);
    }

    @Transactional
    @Override
    public Estado salva(Estado estado) {
        return entityManager.merge(estado);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Estado estado = buscaPeloId(id);
        if (estado == null) {
            throw new EmptyResultDataAccessException(1);
        }
        entityManager.remove(estado);
    }
}
