package com.culysoft.algafood.infrastracture;

import com.culysoft.algafood.domain.exception.EntidadeEmUsoException;
import com.culysoft.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.repository.CozinhaRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Cozinha> todasCozinhas() {
        TypedQuery<Cozinha> typedQuery = entityManager.createQuery("FROM Cozinha c", Cozinha.class);

        return typedQuery.getResultList();
    }

    @Override
    public List<Cozinha> consultaPeloNome(String nome) {
        return entityManager.createQuery("FROM Cozinha c WHERE c.nome LIKE :nome", Cozinha.class)
                .setParameter("nome", "%" +nome+ "%")
                .getResultList();
    }


    @Override
    public Cozinha buscaPeloId(Long id) {
        Cozinha cozinha = entityManager.find(Cozinha.class, id);
        if (cozinha == null)
            throw new EntidadeNaoEncontradaException(
                    String.format("Nao existe cadastro de cozinha com codigo %d", id));
        return cozinha;
    }

    @Transactional
    @Override
    public Cozinha salva(Cozinha cozinha) {
        return entityManager.merge(cozinha);
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
