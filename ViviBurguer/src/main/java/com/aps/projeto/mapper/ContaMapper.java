package com.aps.projeto.mapper;

import com.aps.projeto.negocio.Conta;
import com.aps.projeto.negocio.ContaRequest;

import java.util.concurrent.atomic.AtomicLong;

public class ContaMapper {
    public static Conta ContaRequestToConta(long id, ContaRequest contaRequest) {
        return new Conta(
                id,
                contaRequest.getNome(),
                contaRequest.getCpf(),
                contaRequest.getTelefone(),
                contaRequest.getEmail(),
                contaRequest.getSenha(),
                contaRequest.getEndereco());
    }
}
