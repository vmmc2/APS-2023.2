package com.aps.projeto.negocio.mapper;

import com.aps.projeto.negocio.entity.Conta;
import com.aps.projeto.negocio.entity.ContaDTO;

public class ContaMapper {
  public static ContaDTO contaToContaDto(Conta conta) {
    return new ContaDTO.Builder()
        .nome(conta.getNome())
        .cpf(conta.getCpf().getCpf())
        .telefone(conta.getTelefone())
        .email(conta.getEmail())
        .senha(conta.getSenha())
        .build();
  }
}
