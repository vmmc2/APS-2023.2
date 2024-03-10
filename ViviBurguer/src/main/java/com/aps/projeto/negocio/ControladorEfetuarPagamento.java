package com.aps.projeto.negocio;

import com.aps.projeto.negocio.adapter.IComunicacaoOperadoraCartaoAdapter;
import com.aps.projeto.negocio.entity.Carrinho;
import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.Comprovante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControladorEfetuarPagamento {
  private final IComunicacaoOperadoraCartaoAdapter iComunicacaoOperadoraCartaoAdapter;
  public Comprovante efetuarPagamento(Cartao cartao, Carrinho carrinho) {
    return iComunicacaoOperadoraCartaoAdapter.efetuarPagamento(cartao, carrinho.getValorPagamento(), carrinho.getItens());
  }
}
