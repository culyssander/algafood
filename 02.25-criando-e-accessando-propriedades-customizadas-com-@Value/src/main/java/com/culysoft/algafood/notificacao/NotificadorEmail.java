package com.culysoft.algafood.notificacao;

import com.culysoft.algafood.modelo.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@TipoNotificadocao
@Component
public class NotificadorEmail implements Notificador {

    @Value("${notificador.email.host-servidor}")
    private String host;

    @Value("${notificador.email.porta-servidor}")
    private Integer porta;

    public NotificadorEmail() {
        System.out.println("NotificadorEmail");
    }
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Host: " + host);
        System.out.println("Porta: " + porta);
        System.out.printf("Notificar o %s por email %s: %s \n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }

}
