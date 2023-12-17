package com.aps.projeto.comunicacao;

import com.aps.projeto.negocio.Fachada;
import com.aps.projeto.negocio.pojos.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignInController {
  private final Fachada fachada;

  @PostMapping("/conta/signIn")
  public SignInResponse efetuarSignIn(@RequestParam("email") String email, @RequestParam("senha") String senha) {
    return fachada.efetuarSignIn(email, senha);
  }
}
