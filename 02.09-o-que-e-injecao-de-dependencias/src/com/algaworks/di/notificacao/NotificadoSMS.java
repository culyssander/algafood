package com.algaworks.di.notificacao;

import com.algaworks.di.modelo.Cliente;

public class NotificadoSMS implements Notificador {

    @Override
    public void notificar(Cliente cliente, String m) {
        System.out.printf("Notificando %s por SMS ateas do telefone %s\n",
                cliente.getNome(), cliente.getTelefone());
    }
}
