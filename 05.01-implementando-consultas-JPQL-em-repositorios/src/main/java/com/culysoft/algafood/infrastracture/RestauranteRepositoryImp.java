package com.culysoft.algafood.infrastracture;

import com.culysoft.algafood.domain.exception.EntidadeEmUsoException;
import com.culysoft.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.RestauranteRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class RestauranteRepositoryImp implements RestauranteRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> todosRestaurantes() {
        TypedQuery<Restaurante> query = entityManager.createQuery("FROM Restaurante", Restaurante.class);
        return query.getResultList();
    }

    @Override
    public Restaurante buscaPeloId(Long id) {
        return entityManager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public Restaurante salva(Restaurante restaurante) {
        return entityManager.merge(restaurante);
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
