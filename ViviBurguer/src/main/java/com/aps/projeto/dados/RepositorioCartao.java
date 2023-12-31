package com.aps.projeto.dados;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.CartaoDTO;
import com.aps.projeto.negocio.pojos.CPF;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RepositorioCartao implements IRepositorioCartao {
  private final CartaoDAO cartaoDAO;
  @Override
  public boolean registrarCartao(Cartao cartao) {
    cartaoDAO.save(cartao);
    return true;
  }

  @Override
  public boolean existeCartao(Cartao cartao) {
    return cartaoDAO.existsByCpf(cartao.getCpf());
  }

  @Override
  public List<Cartao> encontrar(CPF cpf) {
    return cartaoDAO.findAllByCpf(cpf);
  }
}
