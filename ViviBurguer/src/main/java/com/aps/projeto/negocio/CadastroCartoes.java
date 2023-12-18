package com.aps.projeto.negocio;
import com.aps.projeto.dados.IRepositorioCartao;
import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.CartaoDTO;
import com.aps.projeto.negocio.pojos.CPF;
import java.util.List;
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

  public List<Cartao> exibirCartoes(CPF cpf) {
    return repositorioCartao.encontrar(cpf);
  }
}
