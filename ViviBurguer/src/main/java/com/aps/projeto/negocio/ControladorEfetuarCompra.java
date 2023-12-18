package com.aps.projeto.negocio;

import com.aps.projeto.comunicacao.ComunicacaoOperadoraCartao;
import com.aps.projeto.negocio.adapter.ComunicacaoOperadoraCartaoAdapter;
import com.aps.projeto.negocio.entity.Carrinho;
import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.Comprovante;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControladorEfetuarCompra {
  private final ComunicacaoOperadoraCartaoAdapter comunicacaoOperadoraCartaoAdapter;
  public Comprovante efetuarCompra(Cartao cartao, Carrinho carrinho) {
    return comunicacaoOperadoraCartaoAdapter.efetuarCompra(cartao, carrinho.getValorCompra());
  }
}
