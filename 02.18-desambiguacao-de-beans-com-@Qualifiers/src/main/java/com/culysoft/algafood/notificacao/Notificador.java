package com.culysoft.algafood.notificacao;

import com.culysoft.algafood.modelo.Cliente;

public interface Notificador {

    void notificar(Cliente cliente, String mensagem);
}
