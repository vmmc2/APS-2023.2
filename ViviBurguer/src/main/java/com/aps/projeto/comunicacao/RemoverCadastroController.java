package com.aps.projeto.comunicacao;

import com.aps.projeto.negocio.Fachada;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RemoverCadastroController {
  private final Fachada fachada;
  @DeleteMapping("/conta/remover")
  public String removerConta(@RequestParam("email") String email, @RequestParam("senha") String senha) {
    return fachada.removerConta(email, senha);
  }
}
