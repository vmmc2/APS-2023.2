package com.aps.projeto.dados;

import com.aps.projeto.negocio.entity.Comprovante;
import com.aps.projeto.negocio.pojos.CPF;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepositorioComprovante implements IRepositorioComprovante {
  private final ComprovanteDAO comprovanteDAO;
  @Override
  public List<Comprovante> encontrar(CPF cpf) {
    return comprovanteDAO.findAllByCpf(cpf);
  }
  @Override
  public boolean registrarComprovante(Comprovante comprovante) {
    comprovanteDAO.save(comprovante);
    return true;
  }
}
