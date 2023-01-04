package com.culysoft.algafood.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;
}
