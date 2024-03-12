package br.com.viviburguer.accountregister.negocio;

import br.com.viviburguer.accountregister.negocio.exceptions.ForbiddenException;
import br.com.viviburguer.accountregister.negocio.exceptions.NotFoundException;
import br.com.viviburguer.common.negocio.entity.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControladorSignIn {

    private final CadastroContas cadastroContas;
    public Conta efetuarSignIn(String email, String senha) {
        if(cadastroContas.existeConta(email, senha)) {
            Conta conta = cadastroContas.validarCredenciaisConta(email, senha);
            if (conta != null) {
                return conta;
            } else {
                throw new ForbiddenException("Email e senha não se correspodem");
            }
        } else {
            throw new NotFoundException("Email não existe");
        }
    }
}
