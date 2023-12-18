package com.aps.projeto.comunicacao;

import com.aps.projeto.negocio.entity.Cartao;
import org.springframework.stereotype.Component;

@Component
public class ComunicacaoOperadoraCartao {

  public boolean existeCartao(Cartao cartao) {
    return false;
  }
}
