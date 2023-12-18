package com.aps.projeto.negocio;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.Conta;
import com.aps.projeto.negocio.pojos.BasicResponse;
import com.aps.projeto.negocio.pojos.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Fachada {
    private final ControladorSignUp controladorSignUp;
    private final ControladorSignIn controladorSignIn;
    private final ControladorRemoverCadastro controladorRemoverCadastro;
    private final ControldorAdicionarCartao controldorAdicionarCartao;

    public BasicResponse efetuarSignUp(Conta conta) {
        return controladorSignUp.efetuarSignUp(conta);
    }

    public Conta exibirConta(String email) {
        return controladorSignUp.exibir(email);
    }

    public SignInResponse efetuarSignIn(String email, String senha) {
        return controladorSignIn.efetuarSignIn(email, senha);
    }

    public BasicResponse removerConta(String email, String senha) {
        return controladorRemoverCadastro.removerConta(email, senha);
    }

    public BasicResponse adicionarCartao(Cartao cartao) {
        return controldorAdicionarCartao.adicionarCartao(cartao);

    }
}
