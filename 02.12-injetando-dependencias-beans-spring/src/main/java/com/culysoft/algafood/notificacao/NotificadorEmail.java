package com.culysoft.algafood.notificacao;

import com.culysoft.algafood.modelo.Cliente;
import org.springframework.stereotype.Component;

@Component
public class NotificadorEmail implements Notificador {

    public NotificadorEmail() {
        System.out.println("Iniciando o objecto NotificadorEmail");
    }
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificar o %s por email %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
