package br.com.viviburguer.accountregister.negocio;

import br.com.viviburguer.accountregister.negocio.exceptions.BadRequestException;
import br.com.viviburguer.common.negocio.entity.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControladorSignUp {
    private final CadastroContas cadastroContas;
    private static final  String CADASTRO_SUCESSO = "Conta cadastrada com sucesso!";
    private static final  String CADASTRO_FALHA = "Não foi possível cadastrar a conta";
    private static final  String CADASTRO_EXISTE = "Conta já existe";

    public Conta efetuarSignUp(Conta conta) {
        if(!cadastroContas.existeEmail(conta.getEmail())) {
            if (cadastroContas.registrarConta(conta)) {
                return conta;
            } else {
                throw new RuntimeException(CADASTRO_FALHA);
            }
        } else {
            throw new BadRequestException(CADASTRO_EXISTE);
        }
    }
}

