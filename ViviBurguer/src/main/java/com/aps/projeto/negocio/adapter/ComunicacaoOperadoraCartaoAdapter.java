package com.aps.projeto.negocio.adapter;

import static com.aps.projeto.negocio.mapper.CartaoMapper.cartaoToCartaoDto;

import com.aps.projeto.comunicacao.ComunicacaoOperadoraCartao;
import com.aps.projeto.comunicacao.Observer.EventManager;
import com.aps.projeto.comunicacao.Observer.SalvarComprovanteListener;
import com.aps.projeto.negocio.CadastroComprovante;
import com.aps.projeto.negocio.ControladorSalvarComprovante;
import com.aps.projeto.negocio.converter.StatusConverter;
import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.Comprovante;
import com.aps.projeto.negocio.entity.Item;
import com.aps.projeto.negocio.entity.PagamentoOperadoraCartao;
import com.aps.projeto.negocio.enumerators.Status;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ComunicacaoOperadoraCartaoAdapter implements IComunicacaoOperadoraCartaoAdapter {
  private final ComunicacaoOperadoraCartao comunicacaoOperadoraCartao;
  private final EventManager eventManager;
  private final static String SALVAR_COMPROVANTE = "Salvar_Comprovante";

  public ComunicacaoOperadoraCartaoAdapter(ComunicacaoOperadoraCartao comunicacaoOperadoraCartao, EventManager eventManager, ControladorSalvarComprovante controladorSalvarComprovante) {
    this.comunicacaoOperadoraCartao = comunicacaoOperadoraCartao;
    this.eventManager = eventManager;
    this.eventManager.subscribe(SALVAR_COMPROVANTE, new SalvarComprovanteListener(controladorSalvarComprovante));
  }
  @Override
  public boolean existeCartao(Cartao cartao) {
    ResponseEntity<String> response = comunicacaoOperadoraCartao.existeCartao(cartaoToCartaoDto(cartao));
    return response.getStatusCode().is2xxSuccessful();
  }
  @Override
  public Comprovante efetuarPagamento(Cartao cartao, BigDecimal valorCompra, List<Item> items) {
    PagamentoOperadoraCartao pagamento = new PagamentoOperadoraCartao.Builder()
        .numeroCartao(cartao.getNumero())
        .cvv(cartao.getCvv())
        .cpf(cartao.getCpf().getCpf())
        .valorCompra(valorCompra)
        .tipoCompra(cartao.getTipoCartao())
        .build();
    ResponseEntity<Comprovante> response = comunicacaoOperadoraCartao.efetuarPagamento(pagamento);
    Comprovante comprovante = response.getBody();
    assert comprovante != null;
    comprovante.setStatus(StatusConverter.fromHttpStatus((HttpStatus) response.getStatusCode()));
    comprovante.setNomeUsuario(cartao.getTitular());
    comprovante.setCpf(cartao.getCpf());
    comprovante.setValorPagamento(valorCompra);
    comprovante.setItens(items);
    comprovante.setDataOperacao(LocalDateTime.now());
    if(comprovante.getStatus() == Status.OK) {
      eventManager.notify(SALVAR_COMPROVANTE, comprovante);
    }
    return comprovante;
  }
}
