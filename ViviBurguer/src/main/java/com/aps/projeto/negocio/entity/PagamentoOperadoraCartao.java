package com.aps.projeto.negocio.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoOperadoraCartao {
  private String numeroCartao;
  private String cvv;
  private String cpf;
  private BigDecimal valorCompra;
  private String tipoCompra;
}
