package com.aps.projeto.negocio;

import com.aps.projeto.negocio.pojos.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Fachada {
    private final ControladorSignUp controladorSignUp;
    private final ControladorSignIn controladorSignIn;
    private final ControladorRemoverCadastro controladorRemoverCadastro;

    public String efetuarSignUp(Conta conta) {
        return controladorSignUp.efetuarSignUp(conta);
    }

    public Conta exibirConta(String email) {
        return controladorSignUp.exibir(email);
    }

    public SignInResponse efetuarSignIn(String email, String senha) {
        return controladorSignIn.efetuarSignIn(email, senha);
    }

    public String removerConta(String email, String senha) {
        return controladorRemoverCadastro.removerConta(email, senha);
    }
}
