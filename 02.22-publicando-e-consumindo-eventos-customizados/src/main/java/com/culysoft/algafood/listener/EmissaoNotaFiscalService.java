package com.culysoft.algafood.listener;

import com.culysoft.algafood.service.ClienteAtivadoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        System.out.println("Emitindo nota para cliente " + event.getCliente().getNome());
    }
}
