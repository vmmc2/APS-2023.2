package com.aps.projeto.negocio;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControladorRemoverCadastro {
  private static final String CONTA_NOT_FOUND = "Conta não existe";
  private static final String REMOVE_FORBIDDEN = "Email e senha não correspodem";
  private static final String REMOVE_OK = "Conta deletada!";

  private final CadastroContas cadastroContas;
  public String removerConta(String email, String senha) {
    if(cadastroContas.existeConta(email)) {
      Conta conta = cadastroContas.validarCredenciaisConta(email, senha);
      if(conta != null) {
        cadastroContas.apagarConta(email);
        return REMOVE_OK;
      }
      return REMOVE_FORBIDDEN;
    } else {
      return CONTA_NOT_FOUND;
    }
  }
}
