package com.aps.projeto.comunicacao;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.Fachada;
import com.aps.projeto.negocio.entity.CartaoDTO;
import com.aps.projeto.negocio.adapter.CartaoAdapter;
import com.aps.projeto.negocio.pojos.BasicResponse;
import com.aps.projeto.negocio.pojos.CPF;
import com.aps.projeto.security.JwtTokenProvider;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
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
  @GetMapping("/cartao/get")
  public ResponseEntity<List<CartaoDTO>> exibirCartoes(@RequestHeader("Authorization") String Authorization, @RequestParam("cpf") CPF cpf) {
    if(!jwtTokenProvider.validateToken(Authorization.split(" ")[1])) {
      return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
    List<CartaoDTO> cartoes = fachada.exibirCartoes(cpf)
        .stream()
        .map(CartaoAdapter::cartaoToCartaoDto)
        .collect(Collectors.toList());

    if (cartoes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(cartoes);
  }

}
