package com.culysoft.algafood.infrastructure.repository;

import com.culysoft.algafood.domain.model.Restaurante;
import com.culysoft.algafood.domain.repository.RestauranteRepositoryQuery;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQuery {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Restaurante> find(String nome, BigDecimal taxaInicial,
                                   BigDecimal taxaFinal) {
        var parametros = new HashMap<String, Object>();
        var jpql = new StringBuilder("FROM Restaurante WHERE 0 = 0 ");

        if (StringUtils.hasLength(nome)) {
            jpql.append("AND nome LIKE :nome ");
            parametros.put("nome", "%" + nome + "%");
        }

        if (taxaInicial != null) {
            jpql.append("AND taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial", taxaInicial);
        }

        if (taxaFinal != null) {
            jpql.append("AND taxaFrete <= :taxaFinal");
            parametros.put("taxaFinal", taxaFinal);
        }

        TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);
        parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
        return query.getResultList();
    }
}
