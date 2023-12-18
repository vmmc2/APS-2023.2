package com.aps.projeto.negocio.mapper;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.CartaoDTO;
import com.aps.projeto.negocio.pojos.CPF;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class CartaoMapper {

  public static Cartao cartaoDtoToCartao(CartaoDTO cartaoDTO) {
    String[] data = cartaoDTO.getDataValidade().split("-");
    return new Cartao()
        .setNumero(cartaoDTO.getNumero())
        .setCvv(cartaoDTO.getCvv())
        .setTitular(cartaoDTO.getTitular())
        .setDataValidade(YearMonth.of(Integer.parseInt(data[0]), Integer.parseInt(data[1])))
        .setBandeira(cartaoDTO.getBandeira())
        .setTipoCartao(cartaoDTO.getTipoCartao())
        .setCpf(new CPF(cartaoDTO.getCpf()));
  }
  public static CartaoDTO cartaoToCartaoDto(Cartao cartao) {
    return new CartaoDTO()
        .setNumero(cartao.getNumero())
        .setCvv(cartao.getCvv())
        .setTitular(cartao.getTitular())
        .setDataValidade(cartao.getDataValidade().getYear() + "-" + cartao.getDataValidade().getMonth().getValue())
        .setBandeira(cartao.getBandeira())
        .setTipoCartao(cartao.getTipoCartao())
        .setCpf(cartao.getCpf().getCpf());
  }
}
