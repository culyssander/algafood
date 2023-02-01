package com.culysoft.algafood.domain.repository;

import com.culysoft.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
//    List<Cozinha> consultaPeloNome(String nome);
    List<Cozinha> findByNomeContaining(String nome);

    boolean existsByNome(String nome);

}
