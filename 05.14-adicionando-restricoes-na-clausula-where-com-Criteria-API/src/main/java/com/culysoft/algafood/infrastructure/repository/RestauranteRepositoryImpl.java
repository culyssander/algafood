package com.culysoft.algafood.infrastructure.repository;

import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.RestauranteRepositoryQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restaurante> find(String nome, BigDecimal taxaInicial,
                                   BigDecimal taxaFinal) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);

        Predicate nomePredicate = criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        Predicate taxaInicialPredicate = criteriaBuilder
                .greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial);
        Predicate taxaFinalPredicate = criteriaBuilder
                .lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal);

        criteriaQuery.where(nomePredicate, taxaInicialPredicate, taxaFinalPredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
