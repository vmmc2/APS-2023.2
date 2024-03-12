package br.com.viviburguer.common.negocio.entity;

import br.com.viviburguer.common.negocio.converter.CPFConverter;
import br.com.viviburguer.common.negocio.pojos.CPF;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cartao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String numero;
  private String cvv;
  private String titular;
  private YearMonth dataValidade;
  private String bandeira;
  private String tipoCartao;
  @Convert(converter = CPFConverter.class)
  private CPF cpf;
  public static class Builder {
    private Cartao cartao;

    public Builder() {
      cartao = new Cartao();
    }
    public Builder numero(String numero) {
      cartao.numero = numero;
      return this;
    }

    public Builder cvv(String cvv) {
      cartao.cvv = cvv;
      return this;
    }

    public Builder titular(String titular) {
      cartao.titular = titular;
      return this;
    }

    public Builder dataValidade(YearMonth dataValidade) {
      cartao.dataValidade = dataValidade;
      return this;
    }

    public Builder bandeira(String bandeira) {
      cartao.bandeira = bandeira;
      return this;
    }

    public Builder tipoCartao(String tipoCartao) {
      cartao.tipoCartao = tipoCartao;
      return this;
    }

    public Builder cpf(CPF cpf) {
      cartao.cpf = cpf;
      return this;
    }
    public Cartao build() {
      return cartao;
    }
  }
}
