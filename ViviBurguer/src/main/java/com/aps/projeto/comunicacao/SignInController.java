package com.aps.projeto.comunicacao;

import com.aps.projeto.negocio.Fachada;
import com.aps.projeto.negocio.pojos.SignInResponse;
import com.aps.projeto.security.JwtTokenProvider;
import java.net.http.HttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignInController {
  private final Fachada fachada;
  private final JwtTokenProvider jwtTokenProvider;

  @PostMapping("/conta/signIn")
  public ResponseEntity<SignInResponse> efetuarSignIn(@RequestParam("email") String email, @RequestParam("senha") String senha) {
    SignInResponse signInResponse = fachada.efetuarSignIn(email, senha);
    String token = jwtTokenProvider.generateToken(email);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + token);
    return new ResponseEntity<>(signInResponse, headers, HttpStatus.OK);

  }
}
