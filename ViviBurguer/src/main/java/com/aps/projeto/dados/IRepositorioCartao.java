package com.aps.projeto.dados;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.CartaoDTO;
import com.aps.projeto.negocio.entity.Conta;
import com.aps.projeto.negocio.pojos.CPF;
import java.util.List;

public interface IRepositorioCartao {
  boolean registrarCartao(Cartao cartao);
  boolean existeCartao(Cartao cartao);

  List<Cartao> encontrar(CPF cpf);
}
