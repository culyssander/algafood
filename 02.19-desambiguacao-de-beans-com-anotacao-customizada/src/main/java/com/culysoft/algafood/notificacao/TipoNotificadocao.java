package com.culysoft.algafood.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface TipoNotificadocao {

    NivelNotificacao value() default NivelNotificacao.NORMAL;
}
