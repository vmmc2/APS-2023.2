package com.aps.projeto.comunicacao;

import static com.aps.projeto.negocio.converter.StatusConverter.toHttpStatus;

import com.aps.projeto.negocio.converter.StatusConverter;
import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.Fachada;
import com.aps.projeto.negocio.entity.CartaoDTO;
import com.aps.projeto.negocio.enumerators.Status;
import com.aps.projeto.negocio.mapper.CartaoMapper;
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
  public ResponseEntity<BasicResponse> adicionarCartao(@RequestHeader("Authorization") String Authorization, @RequestBody Cartao cartao) {
    if(!jwtTokenProvider.validateToken(Authorization.split(" ")[1])) {
      return new ResponseEntity<>(new BasicResponse("Acesso Negado", Status.FORBIDDEN), HttpStatus.FORBIDDEN);
    }
    BasicResponse response = fachada.adicionarCartao(cartao);
    return new ResponseEntity<>(response, toHttpStatus(response.getStatus()));
  }
  @GetMapping("/cartao/get")
  public ResponseEntity<List<CartaoDTO>> exibirCartoes(@RequestHeader("Authorization") String Authorization, @RequestParam("cpf") CPF cpf) {
    if(!jwtTokenProvider.validateToken(Authorization.split(" ")[1])) {
      return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
    List<CartaoDTO> cartoes = fachada.exibirCartoes(cpf)
        .stream()
        .map(CartaoMapper::cartaoToCartaoDto)
        .collect(Collectors.toList());

    if (cartoes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.ok(cartoes);
  }

}
