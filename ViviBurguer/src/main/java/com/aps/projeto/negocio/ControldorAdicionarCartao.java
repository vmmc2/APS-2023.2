package com.aps.projeto.negocio;

import com.aps.projeto.comunicacao.ComunicacaoOperadoraCartao;
import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.pojos.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControldorAdicionarCartao {
  private final CadastroCartoes cadastroCartoes;
  private final ComunicacaoOperadoraCartao comunicacaoOperadoraCartao;
  private final static String CAD_CARD_SUCESSO = "Cartão salvo com sucesso";
  private final static String CAD_CARD_FALHA = "Não foi possível salvar o cartão";
  private final static String CAD_CARD_INVALID = "O cartão é inválido";
  private final static String CAD_CARD_EXISTS = "O cartão já existe";
  public BasicResponse adicionarCartao(Cartao cartao) {
    if(!cadastroCartoes.existeCartao(cartao)) {
      if(comunicacaoOperadoraCartao.existeCartao(cartao).getStatus().equals(HttpStatus.OK)) {
        if(cadastroCartoes.registrarCartao(cartao)) {
          return new BasicResponse(CAD_CARD_SUCESSO, HttpStatus.CREATED);
        } else {
          return new BasicResponse(CAD_CARD_FALHA, HttpStatus.INTERNAL_SERVER_ERROR);
        }
      } else {
        return new BasicResponse(CAD_CARD_INVALID, HttpStatus.BAD_REQUEST);
      }
    } else {
      return new BasicResponse(CAD_CARD_EXISTS, HttpStatus.BAD_REQUEST);
    }
  }
}
