package com.aps.projeto.negocio;

import com.aps.projeto.negocio.entity.Carrinho;
import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.Comprovante;
import com.aps.projeto.negocio.entity.Conta;
import com.aps.projeto.negocio.pojos.BasicResponse;
import com.aps.projeto.negocio.pojos.CPF;
import com.aps.projeto.negocio.pojos.SignInResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Fachada {
    private final ControladorSignUp controladorSignUp;
    private final ControladorSignIn controladorSignIn;
    private final ControladorRemoverCadastro controladorRemoverCadastro;
    private final ControladorAdicionarCartao controladorAdicionarCartao;
    private final ControladorEfetuarPagamento controladorEfetuarPagamento;

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
        return controladorAdicionarCartao.adicionarCartao(cartao);

    }

    public List<Cartao> exibirCartoes(CPF cpf) {
        return controladorAdicionarCartao.exibirCartoes(cpf);
    }

    public Comprovante efetuarPagamento(Cartao cartao, Carrinho carrinho) {
        return controladorEfetuarPagamento.efetuarPagamento(cartao, carrinho);
    }
}
