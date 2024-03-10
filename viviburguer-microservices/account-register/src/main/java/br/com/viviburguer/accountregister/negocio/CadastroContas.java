package br.com.viviburguer.accountregister.negocio;

import br.com.viviburguer.accountregister.data.IRepositorioConta;
import br.com.viviburguer.common.negocio.entity.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastroContas {
    private final IRepositorioConta repositorioConta;
    public boolean registrarConta(Conta conta) {
        return repositorioConta.registrarConta(conta);
    }

    public boolean existeConta(String email) {
        return repositorioConta.existeConta(email);

    }
    public Conta exibir(String email) {
        return repositorioConta.encontrar(email);
    }
    public Conta validarCredenciaisConta(String email, String senha) {
        Conta conta = repositorioConta.encontrar(email);
        if(conta.getSenha().equals(senha)) {
            return conta;
        }
        return null;
    }
    public void apagarConta(String email) {
        repositorioConta.remover(email);
    }
}