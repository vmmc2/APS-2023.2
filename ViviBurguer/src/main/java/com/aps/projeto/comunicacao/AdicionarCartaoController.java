package com.aps.projeto.comunicacao;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.Fachada;
import com.aps.projeto.negocio.pojos.BasicResponse;
import com.aps.projeto.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdicionarCartaoController {
  private final Fachada fachada;
  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/cartao/adicionar")
  public ResponseEntity<String> adicionarCartao(@RequestHeader("Authorization") String Authorization, @RequestBody Cartao cartao) {
    if(!jwtTokenProvider.validateToken(Authorization.split(" ")[1])) {
      return new ResponseEntity<>("Acesso Negado", HttpStatus.FORBIDDEN);
    }
    BasicResponse response = fachada.adicionarCartao(cartao);
    return new ResponseEntity<>(response.getMessage(), response.getStatus());
  }

}
