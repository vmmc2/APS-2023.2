package com.aps.projeto.negocio.entity;

import com.aps.projeto.negocio.converter.CPFConverter;
import com.aps.projeto.negocio.pojos.CPF;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.YearMonth;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cartao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @NotNull
  @Size(min = 16, max = 16)
  @Pattern(regexp = "\\d+", message = "O número do cartão deve conter apenas dígitos")
  private String numero;

  @NotNull
  @Size(min = 3, max = 3)
  @Pattern(regexp = "\\d+", message = "O CVV deve conter apenas dígitos")
  private String cvv;

  @NotBlank
  @Size(max = 255)
  private String titular;

  @NotBlank
  private YearMonth dataValidade;

  @NotBlank
  @Size(max = 255)
  private String bandeira;

  @NotBlank
  @Size(max = 255)
  private String tipoCartao;

  @NotNull
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
