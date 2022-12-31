package com.culysoft.algafood;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("notificador.email")
@Component
public class Properties {

    private String hostServidor;
    private String portaServidor;

    public String getHostServidor() {
        return hostServidor;
    }

    public void setHostServidor(String hostServidor) {
        this.hostServidor = hostServidor;
    }

    public String getPortaServidor() {
        return portaServidor;
    }

    public void setPortaServidor(String portaServidor) {
        this.portaServidor = portaServidor;
    }
}
