package com.culysoft.algafood.infrastracture;

import com.culysoft.algafood.domain.model.Cidade;
import com.culysoft.algafood.domain.model.Cozinha;
import com.culysoft.algafood.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Cidade> listar() {
        return entityManager.createQuery("FROM Cidade c", Cidade.class).getResultList();
    }

    @Override
    public Cidade buscaPeloId(Long id) {
        return entityManager.find(Cidade.class, id);
    }

    @Transactional
    @Override
    public Cidade salva(Cidade cidade) {
        return entityManager.merge(cidade);
    }

    @Transactional
    @Override
    public void remover(Long id) {
        Cidade cidadeActual = buscaPeloId(id);

        if(cidadeActual == null) {
            throw new EmptyResultDataAccessException(1);
        }

        entityManager.remove(cidadeActual);
    }
}
