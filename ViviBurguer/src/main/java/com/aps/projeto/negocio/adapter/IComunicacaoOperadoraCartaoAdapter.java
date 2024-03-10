package com.aps.projeto.negocio.adapter;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.Comprovante;
import com.aps.projeto.negocio.entity.Item;
import java.math.BigDecimal;
import java.util.List;

public interface IComunicacaoOperadoraCartaoAdapter {
  boolean existeCartao(Cartao cartao);
  Comprovante efetuarPagamento(Cartao cartao, BigDecimal valorCompra, List<Item> itens);
}
