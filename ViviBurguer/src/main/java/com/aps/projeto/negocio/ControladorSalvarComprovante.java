package com.aps.projeto.negocio;

import com.aps.projeto.negocio.entity.Comprovante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ControladorSalvarComprovante {
  private final CadastroComprovante cadastroComprovante;
  public Comprovante efetuarRegistro(Comprovante comprovante) {
    cadastroComprovante.registrarComprovante(comprovante);
    return comprovante;
  }
}
