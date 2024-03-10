package com.aps.projeto.comunicacao;
import com.aps.projeto.negocio.entity.CartaoDTO;
import com.aps.projeto.negocio.entity.Comprovante;
import com.aps.projeto.negocio.entity.PagamentoOperadoraCartao;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ComunicacaoOperadoraCartao {
  private final static String VALIDATE_PATH = "/validate";
  private final static String BUY_PATH = "/buy";
  private final String endpoint;
  private final RestTemplate restTemplate;

  public ComunicacaoOperadoraCartao(@Value("${creditcard.endpoint}") String endpoint) {
    this.endpoint = endpoint;
    this.restTemplate = new RestTemplate();
  }

  public ResponseEntity<String> existeCartao(CartaoDTO cartaoDTO) {
    String url = endpoint + VALIDATE_PATH;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<CartaoDTO> request = new HttpEntity<>(cartaoDTO, headers);

    return restTemplate.postForEntity(url, request, String.class);
  }

  public ResponseEntity<Comprovante> efetuarPagamento(PagamentoOperadoraCartao pagamento) {
    String url = endpoint + BUY_PATH;

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<PagamentoOperadoraCartao> request = new HttpEntity<>(pagamento, headers);

    return restTemplate.postForEntity(url, request, Comprovante.class);
    //Comprovante comprovante = new Comprovante();
    //comprovante.setId(UUID.randomUUID());
    //comprovante.setMessage("");
    //return ResponseEntity.ok(comprovante);
  }
}
