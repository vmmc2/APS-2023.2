package com.aps.projeto.negocio.mapper;

import com.aps.projeto.negocio.entity.Conta;
import com.aps.projeto.negocio.entity.ContaDTO;

public class ContaMapper {
  public static ContaDTO contaToContaDto(Conta conta) {
    return new ContaDTO()
        .setNome(conta.getNome())
        .setCpf(conta.getCpf().getCpf())
        .setTelefone(conta.getTelefone())
        .setEmail(conta.getEmail())
        .setSenha(conta.getSenha())
        .setEndereco(conta.getEndereco());
  }
}
