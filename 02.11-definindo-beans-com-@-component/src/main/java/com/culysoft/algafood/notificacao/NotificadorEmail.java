package com.culysoft.algafood.notificacao;

import com.culysoft.algafood.modelo.Cliente;
import org.springframework.stereotype.Component;

@Component
public class NotificadorEmail {

    public NotificadorEmail() {
        System.out.println("Iniciando o objecto");
    }
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificar o %s por email %s: %s",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
