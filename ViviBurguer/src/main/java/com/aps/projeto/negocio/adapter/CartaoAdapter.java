package com.aps.projeto.negocio.adapter;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.CartaoDTO;
import com.aps.projeto.negocio.pojos.CPF;
import java.time.YearMonth;

public class CartaoAdapter {

  public static Cartao cartaoDtoToCartao(CartaoDTO cartaoDTO) {
    String[] data = cartaoDTO.getDataValidade().split("-");
    return new Cartao.Builder()
        .numero(cartaoDTO.getNumero())
        .cvv(cartaoDTO.getCvv())
        .titular(cartaoDTO.getTitular())
        .dataValidade(YearMonth.of(Integer.parseInt(data[0]), Integer.parseInt(data[1])))
        .bandeira(cartaoDTO.getBandeira())
        .tipoCartao(cartaoDTO.getTipoCartao())
        .cpf(new CPF(cartaoDTO.getCpf()))
        .build();
  }

  public static CartaoDTO cartaoToCartaoDto(Cartao cartao) {
    return new CartaoDTO.Builder()
        .numero(cartao.getNumero())
        .cvv(cartao.getCvv())
        .titular(cartao.getTitular())
        .dataValidade(cartao.getDataValidade().getYear() + "-" + getMonth(cartao.getDataValidade().getMonth().getValue()))
        .bandeira(cartao.getBandeira())
        .tipoCartao(cartao.getTipoCartao())
        .cpf(cartao.getCpf().getCpf())
        .build();
  }
  public static String getMonth(int month) {
    String str = String.valueOf(month);
    if(month < 10) {
      str = "0" + str;
    }
    return str;
  }
}
