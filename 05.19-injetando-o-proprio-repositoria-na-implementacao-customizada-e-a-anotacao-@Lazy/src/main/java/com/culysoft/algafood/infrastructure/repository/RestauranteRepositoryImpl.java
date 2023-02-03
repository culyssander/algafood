package com.culysoft.algafood.infrastructure.repository;

import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.RestauranteRepository;
import com.culysoft.algafood.domain.repository.RestauranteRepositoryQuery;
import com.culysoft.algafood.infrastructure.repository.spec.RestauranteSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
import java.util.ArrayList;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired @Lazy
    private RestauranteRepository restauranteRepository;

    public List<Restaurante> find(String nome, BigDecimal taxaInicial,
                                   BigDecimal taxaFinal) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteriaQuery = criteriaBuilder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);

        List<Predicate> arrPredicates = new ArrayList<>();

        if (StringUtils.hasText(nome)) {
            arrPredicates.add(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
        }

        if (taxaInicial != null) {
            arrPredicates.add(criteriaBuilder
                    .greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
        }

        if (taxaFinal != null) {
            arrPredicates.add(criteriaBuilder
                    .lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));
        }

        criteriaQuery.where(arrPredicates.toArray(new Predicate[0]));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Restaurante> findComFreteGratis(String nome) {
        return restauranteRepository.findAll(RestauranteSpec.taxaFreteGratis()
                .and(RestauranteSpec.nomeSemelhantes(nome)));
    }
}
