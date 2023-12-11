package com.aps.projeto.negocio;

import com.aps.projeto.dados.IRepositorioConta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastroConta {
    private final IRepositorioConta repositorioConta;
    public boolean inserir(Conta conta) {
        if(repositorioConta.encontrar(conta.getEmail()) == null) {
            repositorioConta.inserir(conta);
            return true;
        }
        return false;
    }

    public Conta exibir(String email) {
       return repositorioConta.encontrar(email);
    }

}
