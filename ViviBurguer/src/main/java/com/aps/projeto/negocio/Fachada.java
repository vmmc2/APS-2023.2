package com.aps.projeto.negocio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Fachada {
    private final ControladorConta controladorConta;

    public boolean inserirConta(Conta conta) {
        return controladorConta.inserir(conta);
    }

    public Conta exibirConta(String email) {
        return controladorConta.exibir(email);
    }
}
