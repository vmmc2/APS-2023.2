package br.com.viviburguer.accountregister.negocio;

import br.com.viviburguer.accountregister.negocio.exceptions.ForbiddenException;
import br.com.viviburguer.accountregister.negocio.exceptions.NotFoundException;
import br.com.viviburguer.common.negocio.entity.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControladorRemoverCadastro {
    private static final String CONTA_NOT_FOUND = "Conta não existe";
    private static final String REMOVE_FORBIDDEN = "Email e senha não correspodem";

    private final CadastroContas cadastroContas;
    public Boolean removerConta(Conta conta) {
        if(cadastroContas.existeConta(conta.getEmail())) {
            Conta contaDb = cadastroContas.validarCredenciaisConta(conta.getEmail(), conta.getSenha());
            if(contaDb != null) {
                cadastroContas.apagarConta(conta.getEmail());
                return true;
            }
            throw new ForbiddenException(REMOVE_FORBIDDEN);
        } else {
            throw new NotFoundException(CONTA_NOT_FOUND);
        }
    }
}
