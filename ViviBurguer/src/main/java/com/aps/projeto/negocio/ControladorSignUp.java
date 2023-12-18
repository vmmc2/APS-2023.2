package com.aps.projeto.negocio;

import com.aps.projeto.negocio.entity.Conta;
import com.aps.projeto.negocio.enumerators.Status;
import com.aps.projeto.negocio.pojos.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControladorSignUp {
    private final CadastroContas cadastroContas;
    private static final  String CADASTRO_SUCESSO = "Conta cadastrada com sucesso!";
    private static final  String CADASTRO_FALHA = "Não foi possível cadastrar a conta";
    private static final  String CADASTRO_EXISTE = "Conta já existe";

    public BasicResponse efetuarSignUp(Conta conta) {
        if(!cadastroContas.existeConta(conta.getEmail())) {
            boolean status = cadastroContas.registrarConta(conta);
            return status ? new BasicResponse(CADASTRO_SUCESSO, Status.OK) :
                new BasicResponse(CADASTRO_FALHA, Status.INTERNAL_ERROR);
        } else {
            return new BasicResponse(CADASTRO_EXISTE, Status.BAD_REQUEST);
        }
    }

    public Conta exibir(String email) {
        return cadastroContas.exibir(email);
    }
}
