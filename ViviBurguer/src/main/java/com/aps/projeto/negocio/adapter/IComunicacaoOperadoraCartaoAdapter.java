package com.aps.projeto.negocio.adapter;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.Comprovante;
import java.math.BigDecimal;

public interface IComunicacaoOperadoraCartaoAdapter {
  boolean existeCartao(Cartao cartao);
  Comprovante efetuarCompra(Cartao cartao, BigDecimal valorCompra);
}
