package com.aps.projeto.negocio;
import com.aps.projeto.negocio.entity.Cartao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastroCartoes {

  public boolean existeCartao(Cartao cartao) {
    return false;
  }

  public boolean registrarCartao(Cartao cartao) {
    return false;
  }
}
