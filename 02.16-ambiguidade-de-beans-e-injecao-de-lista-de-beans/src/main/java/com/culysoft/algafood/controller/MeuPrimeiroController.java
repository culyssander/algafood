package com.culysoft.algafood.controller;

import com.culysoft.algafood.modelo.Cliente;
import com.culysoft.algafood.service.AtivacaoClienteServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    @Autowired
    private AtivacaoClienteServico ativacaoClienteServico;

    @GetMapping
    @ResponseBody
    public String ativar() {
        Cliente joao = new Cliente("joao", "joao@xyz.com", "0000");
        Cliente maria = new Cliente("maria", "maria@xyz.com", "0000");

        ativacaoClienteServico.ativar(joao, "Ativado");
        ativacaoClienteServico.ativar(maria, "Ativado");
        return "ativado";
    }
}
