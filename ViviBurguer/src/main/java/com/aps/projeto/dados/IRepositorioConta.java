package com.aps.projeto.dados;

import com.aps.projeto.negocio.Conta;

public interface IRepositorioConta {

    void inserir(Conta conta);

    Conta encontrar(String email);
}
