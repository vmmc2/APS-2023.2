package com.aps.projeto.negocio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControladorSignUp {
    private final CadastroContas cadastroContas;
    private static final  String CADASTRO_SUCESSO = "Conta cadastrada com sucesso!";
    private static final  String CADASTRO_FALHA = "Não foi possível cadastrar a conta";
    private static final  String CADASTRO_EXISTE = "Conta já existe";

    public String efetuarSignUp(Conta conta) {
        if(!cadastroContas.existeConta(conta.getEmail())) {
            boolean status = cadastroContas.registrarConta(conta);
            return status ? CADASTRO_SUCESSO : CADASTRO_FALHA;
        } else {
            return CADASTRO_EXISTE;
        }
    }

    public Conta exibir(String email) {
        return cadastroContas.exibir(email);
    }
}
