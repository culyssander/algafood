package com.culysoft.algafood.domain.repository;

import com.culysoft.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>,
                                    RestauranteRepositoryQuery{

    List<Restaurante> findByTaxaFreteBetween (BigDecimal taxaInicial, BigDecimal taxaFinal);
//    List<Restaurante> findByNomeContainingAndCozinhaId (String nome, Long cozinhaId);

//    @Query("FROM Restaurante WHERE nome LIKE %:nome% AND cozinha_id = :id")
    List<Restaurante> consultaPorNomeECozinhaId(String nome, @Param("id") Long cozinha);

    Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);
    List<Restaurante> findTop2RestauranteByNomeContaining(String nome);

    long countByCozinhaId(Long cozinhaId);

}
