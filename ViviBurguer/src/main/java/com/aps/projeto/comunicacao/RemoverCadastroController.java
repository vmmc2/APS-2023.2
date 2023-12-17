package com.aps.projeto.comunicacao;

import com.aps.projeto.negocio.Fachada;
import com.aps.projeto.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RemoverCadastroController {
  private final Fachada fachada;
  private final JwtTokenProvider jwtTokenProvider;
  @DeleteMapping("/conta/remover")
  public ResponseEntity<String> removerConta(@RequestHeader("Authorization") String Authorization, @RequestParam("email") String email, @RequestParam("senha") String senha) {
    if(!jwtTokenProvider.validateToken(Authorization.split(" ")[1])) {
      return new ResponseEntity<>("Acesso Negado", HttpStatus.FORBIDDEN);
    }
    return ResponseEntity.ok(fachada.removerConta(email, senha));
  }
}