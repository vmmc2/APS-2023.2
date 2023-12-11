package com.aps.projeto.dados;

import com.aps.projeto.negocio.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepositorioContaSpring implements IRepositorioConta{
    private final ContaDAO contaDAO;
    @Override
    public void inserir(Conta conta) {
        contaDAO.save(conta);
    }

    @Override
    public Conta encontrar(String email) {
        return contaDAO.findByEmail(email);
    }
}
