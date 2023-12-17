package com.aps.projeto.dados;

import com.aps.projeto.negocio.Conta;

public interface IRepositorioConta {

    boolean registrarConta(Conta conta);
    boolean existeConta(String email);
    Conta encontrar(String email);
    void remover(String email);
}
