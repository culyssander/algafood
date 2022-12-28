package com.algaworks.di;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.NotificadoSMS;
import com.algaworks.di.notificacao.NotificadorEmail;
import com.algaworks.di.service.ActivacaoClienteService;

public class Main {

    public static void main(String[] args) {
        Cliente joao = new Cliente("joao", "joao@xyz.com", "0000000");
        Cliente maria = new Cliente("Maria", "maria@xyz.com", "0000000");

        ActivacaoClienteService activacaoCliente =
                new ActivacaoClienteService(new NotificadoSMS());

        activacaoCliente.activar(joao);
        activacaoCliente.activar(maria);
    }
}
