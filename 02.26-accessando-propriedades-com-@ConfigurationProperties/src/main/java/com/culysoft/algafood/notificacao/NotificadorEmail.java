package com.culysoft.algafood.notificacao;

import com.culysoft.algafood.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@TipoNotificadocao
@Component
public class NotificadorEmail implements Notificador {

    @Autowired
    private NotificadorProperties properties;

    public NotificadorEmail() {
        System.out.println("NotificadorEmail");
    }
    public void notificar(Cliente cliente, String mensagem) {
        System.out.println("Host: " + properties.getHostServidor());
        System.out.println("Porta: " + properties.getPortaServidor());
        System.out.printf("Notificar o %s por email %s: %s \n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }

}
