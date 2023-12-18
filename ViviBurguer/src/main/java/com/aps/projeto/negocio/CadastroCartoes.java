package com.aps.projeto.negocio;
import com.aps.projeto.dados.IRepositorioCartao;
import com.aps.projeto.negocio.entity.Cartao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastroCartoes {
  private final IRepositorioCartao repositorioCartao;
  public boolean existeCartao(Cartao cartao) {
    return repositorioCartao.existeCartao(cartao);
  }

  public boolean registrarCartao(Cartao cartao) {
    return repositorioCartao.registrarCartao(cartao);
  }
}
