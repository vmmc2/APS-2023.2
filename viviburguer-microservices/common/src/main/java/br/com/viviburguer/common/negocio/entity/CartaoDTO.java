package br.com.viviburguer.common.negocio.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartaoDTO {
  private String numero;
  private String cvv;
  private String titular;
  private String dataValidade;
  private String bandeira;
  private String tipoCartao;
  private String cpf;
  public static class Builder {
    private CartaoDTO cartaoDTO;

    public Builder() {
      cartaoDTO = new CartaoDTO();
    }

    public Builder numero(String numero) {
      cartaoDTO.numero = numero;
      return this;
    }

    public Builder cvv(String cvv) {
      cartaoDTO.cvv = cvv;
      return this;
    }

    public Builder titular(String titular) {
      cartaoDTO.titular = titular;
      return this;
    }

    public Builder dataValidade(String dataValidade) {
      cartaoDTO.dataValidade = dataValidade;
      return this;
    }

    public Builder bandeira(String bandeira) {
      cartaoDTO.bandeira = bandeira;
      return this;
    }

    public Builder tipoCartao(String tipoCartao) {
      cartaoDTO.tipoCartao = tipoCartao;
      return this;
    }

    public Builder cpf(String cpf) {
      cartaoDTO.cpf = cpf;
      return this;
    }

    // Método para construir o objeto CartaoDTO
    public CartaoDTO build() {
      return cartaoDTO;
    }
  }
}
