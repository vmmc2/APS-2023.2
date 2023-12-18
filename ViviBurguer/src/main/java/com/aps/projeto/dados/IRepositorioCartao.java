package com.aps.projeto.dados;

import com.aps.projeto.negocio.entity.Cartao;
import com.aps.projeto.negocio.entity.Conta;

public interface IRepositorioCartao {
  boolean registrarCartao(Cartao cartao);
  boolean existeCartao(Cartao cartao);
}
