package br.com.viviburguer.accountregister.data;

import br.com.viviburguer.common.negocio.entity.Conta;

public interface IRepositorioConta {
    boolean registrarConta(Conta conta);
    boolean existeConta(String email, String senha);
    boolean existeEmail(String email);
    Conta encontrar(String email);
    void remover(String email);
}
