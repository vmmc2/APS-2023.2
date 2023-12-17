package com.aps.projeto.dados;

import com.aps.projeto.negocio.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepositorioConta implements IRepositorioConta{
    private final ContaDAO contaDAO;
    @Override
    public boolean registrarConta(Conta conta) {
      contaDAO.save(conta);
      return true;
    }

    @Override
    public boolean existeConta(String email) { return contaDAO.existsByEmail(email); }
    @Override
    public Conta encontrar(String email) {
        return contaDAO.findByEmail(email);
    }
}
