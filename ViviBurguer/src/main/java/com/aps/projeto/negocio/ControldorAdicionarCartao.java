package com.aps.projeto.negocio;

import com.aps.projeto.comunicacao.ComunicacaoOperadoraCartao;
import com.aps.projeto.negocio.adapter.ComunicacaoOperadoraCartaoAdapter;
import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.CartaoDTO;
import com.aps.projeto.negocio.enumerators.Status;
import com.aps.projeto.negocio.pojos.BasicResponse;
import com.aps.projeto.negocio.pojos.CPF;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControldorAdicionarCartao {
  private final CadastroCartoes cadastroCartoes;
  private final ComunicacaoOperadoraCartaoAdapter comunicacaoOperadoraCartaoAdapter;
  private final static String CAD_CARD_SUCESSO = "Cartão salvo com sucesso";
  private final static String CAD_CARD_FALHA = "Não foi possível salvar o cartão";
  private final static String CAD_CARD_INVALID = "O cartão é inválido";
  private final static String CAD_CARD_EXISTS = "O cartão já existe";
  public BasicResponse adicionarCartao(Cartao cartao) {
    if(!cadastroCartoes.existeCartao(cartao)) {
      if(comunicacaoOperadoraCartaoAdapter.existeCartao(cartao)) {
        if(cadastroCartoes.registrarCartao(cartao)) {
          return new BasicResponse(CAD_CARD_SUCESSO, Status.OK);
        } else {
          return new BasicResponse(CAD_CARD_FALHA, Status.INTERNAL_ERROR);
        }
      } else {
        return new BasicResponse(CAD_CARD_INVALID, Status.BAD_REQUEST);
      }
    } else {
      return new BasicResponse(CAD_CARD_EXISTS, Status.BAD_REQUEST);
    }
  }

  public List<Cartao> exibirCartoes(CPF cpf) {
    return cadastroCartoes.exibirCartoes(cpf);
  }
}
