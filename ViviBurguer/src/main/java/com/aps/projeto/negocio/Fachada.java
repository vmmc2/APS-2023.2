package com.aps.projeto.negocio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Fachada {
    private final ControladorSignUp controladorSignUp;

    public String efetuarCadastro(Conta conta) {
        return controladorSignUp.efetuarCadastro(conta);
    }

    public Conta exibirConta(String email) {
        return controladorSignUp.exibir(email);
    }
}
