package br.com.viviburguer.common.negocio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

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
  public static class Builder {
    private PagamentoOperadoraCartao pagamentoCartao;

    public Builder() {
      pagamentoCartao = new PagamentoOperadoraCartao();
    }

    public Builder numeroCartao(String numeroCartao) {
      pagamentoCartao.numeroCartao = numeroCartao;
      return this;
    }

    public Builder cvv(String cvv) {
      pagamentoCartao.cvv = cvv;
      return this;
    }

    public Builder cpf(String cpf) {
      pagamentoCartao.cpf = cpf;
      return this;
    }

    public Builder valorCompra(BigDecimal valorCompra) {
      pagamentoCartao.valorCompra = valorCompra;
      return this;
    }

    public Builder tipoCompra(String tipoCompra) {
      pagamentoCartao.tipoCompra = tipoCompra;
      return this;
    }
    public PagamentoOperadoraCartao build() {
      return pagamentoCartao;
    }
  }
}
