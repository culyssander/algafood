package com.culysoft.algafood.notificacao;

import com.culysoft.algafood.modelo.Cliente;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
public class NotificadorSMS implements Notificador {

    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificar o %s por SMS a atraves do telefone %s: %s \n",
                cliente.getNome(), cliente.getTelefone(), mensagem);
    }

}
