package com.culysoft.algafood.infrastracture;

import com.culysoft.algafood.domain.exception.EntidadeEmUsoException;
import com.culysoft.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.model.Estado;
import com.culysoft.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            entityManager.remove(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Nao existe um cadasto de entidade %d", id)
            );
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Nao podes remover o id %d porque esta em uso", id)
            );
        }
    }
}
