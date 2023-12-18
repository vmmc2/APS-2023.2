package com.aps.projeto.negocio;

import com.aps.projeto.negocio.entity.Conta;
import com.aps.projeto.negocio.enumerators.Status;
import com.aps.projeto.negocio.pojos.BasicResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControladorRemoverCadastro {
  private static final String CONTA_NOT_FOUND = "Conta não existe";
  private static final String REMOVE_FORBIDDEN = "Email e senha não correspodem";
  private static final String REMOVE_OK = "Conta deletada!";

  private final CadastroContas cadastroContas;
  public BasicResponse removerConta(String email, String senha) {
    if(cadastroContas.existeConta(email)) {
      Conta conta = cadastroContas.validarCredenciaisConta(email, senha);
      if(conta != null) {
        cadastroContas.apagarConta(email);
        return new BasicResponse(REMOVE_OK, Status.OK);
      }
      return new BasicResponse(REMOVE_FORBIDDEN, Status.FORBIDDEN);
    } else {
      return new BasicResponse(CONTA_NOT_FOUND, Status.NOT_FOUND);
    }
  }
}
