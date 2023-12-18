package com.aps.projeto.comunicacao;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.CartaoDTO;
import com.aps.projeto.negocio.mapper.CartaoMapper;
import com.aps.projeto.negocio.pojos.BasicResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ComunicacaoOperadoraCartao {
  private final static String VALIDATE_PATH = "/validate";
  private final String endpoint;

  public ComunicacaoOperadoraCartao(@Value("${creditcard.endpoint}") String endpoint) {
    this.endpoint = endpoint;
  }

  public BasicResponse existeCartao(Cartao cartao) {
    RestTemplate restTemplate = new RestTemplate();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<CartaoDTO> request = new HttpEntity<>(CartaoMapper.cartaoToCartaoDto(cartao), headers);
    String url = endpoint + VALIDATE_PATH;
    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

    return new BasicResponse(response.getBody(), (HttpStatus) response.getStatusCode());
  }
}
