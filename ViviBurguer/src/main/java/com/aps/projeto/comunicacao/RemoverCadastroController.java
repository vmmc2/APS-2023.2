package com.aps.projeto.comunicacao;

import static com.aps.projeto.negocio.converter.StatusConverter.toHttpStatus;

import com.aps.projeto.negocio.Fachada;
import com.aps.projeto.negocio.converter.StatusConverter;
import com.aps.projeto.negocio.enumerators.Status;
import com.aps.projeto.negocio.pojos.BasicResponse;
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
  public ResponseEntity<BasicResponse> removerConta(@RequestHeader("Authorization") String Authorization, @RequestParam("email") String email, @RequestParam("senha") String senha) {
    if(!jwtTokenProvider.validateToken(Authorization.split(" ")[1])) {
      return new ResponseEntity<>(new BasicResponse("Acesso Negado", Status.FORBIDDEN), HttpStatus.FORBIDDEN);
    }
    BasicResponse response = fachada.removerConta(email, senha);
    return new ResponseEntity<>(response, toHttpStatus(response.getStatus()));
  }
}