package com.culysoft.algafood.infrastracture;

import com.culysoft.algafood.domain.exception.EntidadeEmUsoException;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> todasCozinhas() {
        TypedQuery<Cozinha> typedQuery = entityManager.createQuery("FROM Cozinha c", Cozinha.class);

        return typedQuery.getResultList();
    }


    @Override
    public Cozinha buscaPeloId(Long id) {
        return entityManager.find(Cozinha.class, id);
    }

    @Transactional
    @Override
    public Cozinha salva(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Cozinha cozinha = buscaPeloId(id);

            if (cozinha == null) {
                throw new EmptyResultDataAccessException(1);
            }

        entityManager.remove(cozinha);
    }
}
