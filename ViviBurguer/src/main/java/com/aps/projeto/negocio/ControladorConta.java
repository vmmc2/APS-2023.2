package com.aps.projeto.negocio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControladorConta {

    private final CadastroConta cadastroConta;

    public boolean inserir(Conta conta) {
        return cadastroConta.inserir(conta);
    }

    public Conta exibir(String email) {
        return cadastroConta.exibir(email);
    }
}
