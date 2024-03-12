package br.com.viviburguer.accountregister.negocio;

import br.com.viviburguer.common.negocio.entity.Conta;
import br.com.viviburguer.common.negocio.pojos.BasicResponse;
import br.com.viviburguer.common.negocio.pojos.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Fachada {
    private final ControladorSignUp controladorSignUp;
    private final ControladorSignIn controladorSignIn;
    private final ControladorRemoverCadastro controladorRemoverCadastro;

    public Conta efetuarSignUp(Conta conta) {
        return controladorSignUp.efetuarSignUp(conta);
    }

    public Conta efetuarSignIn(String email, String senha) {
        return controladorSignIn.efetuarSignIn(email, senha);
    }

    public Boolean removerConta(Conta conta) {
        return controladorRemoverCadastro.removerConta(conta);
    }
}
