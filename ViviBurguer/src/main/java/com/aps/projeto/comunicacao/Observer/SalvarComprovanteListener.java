package com.aps.projeto.comunicacao.Observer;

import com.aps.projeto.dados.IRepositorioComprovante;
import com.aps.projeto.negocio.CadastroComprovante;
import com.aps.projeto.negocio.ControladorSalvarComprovante;
import com.aps.projeto.negocio.entity.Comprovante;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SalvarComprovanteListener implements IEventListener{
  private final ControladorSalvarComprovante controladorSalvarComprovante;
  @Override
  public void update(String eventType, Comprovante comprovante) {
    controladorSalvarComprovante.efetuarRegistro(comprovante);
  }
}
