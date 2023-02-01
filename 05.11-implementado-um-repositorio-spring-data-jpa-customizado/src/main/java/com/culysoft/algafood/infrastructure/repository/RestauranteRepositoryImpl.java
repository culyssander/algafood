package com.culysoft.algafood.infrastructure.repository;

import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.RestauranteRepositoryQuery;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restaurante> find(String nome, BigDecimal taxaInical,
                                   BigDecimal taxaFinal) {
        var jpql = "FROM Restaurante WHERE nome LIKE :nome " +
                "AND taxaFrete BETWEEN :taxaInicial AND :taxaFinal";
        return entityManager.createQuery(jpql, Restaurante.class)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("taxaInicial", taxaInical)
                .setParameter("taxaFinal", taxaFinal)
                .getResultList();
    }
}
