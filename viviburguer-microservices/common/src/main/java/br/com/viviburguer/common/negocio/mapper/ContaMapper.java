package br.com.viviburguer.common.negocio.mapper;

import br.com.viviburguer.common.negocio.entity.Conta;
import br.com.viviburguer.common.negocio.entity.ContaDTO;

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
