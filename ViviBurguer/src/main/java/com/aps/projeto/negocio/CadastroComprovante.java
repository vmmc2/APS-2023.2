package com.aps.projeto.negocio;

import com.aps.projeto.dados.IRepositorioComprovante;
import com.aps.projeto.negocio.entity.Comprovante;
import com.aps.projeto.negocio.pojos.CPF;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastroComprovante {
  private final IRepositorioComprovante repositorioComprovante;

  public boolean registrarComprovante(Comprovante comprovante) {
    return repositorioComprovante.registrarComprovante(comprovante);
  }

  public List<Comprovante> exibirComprovantes(CPF cpf) {
    return repositorioComprovante.encontrar(cpf);
  }
}
