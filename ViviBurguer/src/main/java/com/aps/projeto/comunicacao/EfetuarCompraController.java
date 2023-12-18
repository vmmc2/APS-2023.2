package com.aps.projeto.comunicacao;

import com.aps.projeto.negocio.Fachada;
import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.Compra;
import com.aps.projeto.negocio.entity.Comprovante;
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
public class EfetuarCompraController {
  private final Fachada fachada;
  private final JwtTokenProvider jwtTokenProvider;
  @PostMapping("/compra/efetuar")
  public ResponseEntity<Comprovante> efetuarCompra(@RequestHeader("Authorization") String Authorization, @RequestBody Compra compra) {
    if(!jwtTokenProvider.validateToken(Authorization.split(" ")[1])) {
      return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }
    Comprovante comprovante = fachada.efetuarCompra(compra.getCartao(), compra.getCarrinho());
    return ResponseEntity.ok(comprovante);
  }
}
