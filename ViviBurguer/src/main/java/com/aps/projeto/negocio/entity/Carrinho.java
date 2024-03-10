package com.aps.projeto.negocio.entity;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Carrinho {
  private List<Item> itens;

  public BigDecimal getValorPagamento() {
    return itens.stream().reduce(BigDecimal.ZERO, (subtotal, item) -> subtotal.add(item.getPreco()), BigDecimal::add);
  }
}
