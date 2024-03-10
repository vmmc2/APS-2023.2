package com.aps.projeto.negocio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
  private Cartao cartao;
  private Carrinho carrinho;
}
