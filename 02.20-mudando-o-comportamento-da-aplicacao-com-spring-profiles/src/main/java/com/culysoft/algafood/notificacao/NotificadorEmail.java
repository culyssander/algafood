package com.culysoft.algafood.notificacao;

import com.culysoft.algafood.modelo.Cliente;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@TipoNotificadocao
@Component
public class NotificadorEmail implements Notificador {

    public NotificadorEmail() {
        System.out.println("NotificadorEmail");
    }
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificar o %s por email %s: %s \n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }

}
