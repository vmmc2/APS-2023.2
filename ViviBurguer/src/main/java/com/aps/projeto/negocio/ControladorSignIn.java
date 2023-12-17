package com.aps.projeto.negocio;

import com.aps.projeto.negocio.pojos.SignInResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControladorSignIn {

  private final CadastroContas cadastroContas;
  public SignInResponse efetuarSignIn(String email, String senha) {
    if(cadastroContas.existeConta(email)) {
      Conta conta = cadastroContas.validarCredenciaisConta(email, senha);
      return conta != null ? signInOk(conta) : signInForbidden();
    } else {
      return signInNotFound();
    }
  }

  private SignInResponse signInNotFound() {
    return new SignInResponse()
        .setNome("")
        .setEmail("")
        .setStatus(HttpStatus.NOT_FOUND)
        .setMessage("Email não existe");
  }

  private SignInResponse signInForbidden() {
    return new SignInResponse()
        .setNome("")
        .setEmail("")
        .setStatus(HttpStatus.FORBIDDEN)
        .setMessage("Email e senha não se correspodem");
  }

  private SignInResponse signInOk(Conta conta) {
    return new SignInResponse()
        .setNome(conta.getNome())
        .setEmail(conta.getEmail())
        .setStatus(HttpStatus.OK)
        .setMessage("SignIn realizado com sucesso");
  }
}
