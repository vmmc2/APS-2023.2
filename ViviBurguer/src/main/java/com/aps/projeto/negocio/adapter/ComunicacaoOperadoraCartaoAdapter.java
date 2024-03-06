package com.aps.projeto.negocio.adapter;

import static com.aps.projeto.negocio.mapper.CartaoMapper.cartaoToCartaoDto;

import com.aps.projeto.comunicacao.ComunicacaoOperadoraCartao;
import com.aps.projeto.negocio.converter.StatusConverter;
import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.Comprovante;
import com.aps.projeto.negocio.entity.PagamentoOperadoraCartao;
import com.aps.projeto.negocio.pojos.BasicResponse;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ComunicacaoOperadoraCartaoAdapter implements IComunicacaoOperadoraCartaoAdapter{
  private final ComunicacaoOperadoraCartao comunicacaoOperadoraCartao;
  @Override
  public boolean existeCartao(Cartao cartao) {
    ResponseEntity<String> response = comunicacaoOperadoraCartao.existeCartao(cartaoToCartaoDto(cartao));
    return response.getStatusCode().is2xxSuccessful();
  }
  @Override
  public Comprovante efetuarCompra(Cartao cartao, BigDecimal valorCompra) {
    PagamentoOperadoraCartao pagamento = new PagamentoOperadoraCartao.Builder()
        .numeroCartao(cartao.getNumero())
        .cvv(cartao.getCvv())
        .cpf(cartao.getCpf().getCpf())
        .valorCompra(valorCompra)
        .tipoCompra(cartao.getTipoCartao())
        .build();
    ResponseEntity<Comprovante> response = comunicacaoOperadoraCartao.efetuarCompra(pagamento);
    Comprovante comprovante = response.getBody();
    assert comprovante != null;
    comprovante.setStatus(StatusConverter.fromHttpStatus((HttpStatus) response.getStatusCode()));
    return comprovante;
  }
}
