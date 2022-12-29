package com.culysoft.algafood.notificacao;

import com.culysoft.algafood.modelo.Cliente;

public class NotificadorEmail implements Notificador {

    private boolean caixaAlta;
    private String hostServidorSmtp;

    public NotificadorEmail(String hostServidorSmtp) {
        this.hostServidorSmtp = hostServidorSmtp;
        System.out.println("Iniciando o objecto NotificadorEmail");
    }
    public void notificar(Cliente cliente, String mensagem) {
        if(this.caixaAlta) {
            mensagem = mensagem.toUpperCase();
        }
        System.out.printf("Notificar o %s por email %s: " +
                        "usando SMTP %s %s \n",
                cliente.getNome(), cliente.getEmail(),
                this.hostServidorSmtp, mensagem);
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
}
