package com.aps.projeto.negocio.entity;

import com.aps.projeto.negocio.converter.CPFConverter;
import com.aps.projeto.negocio.pojos.CPF;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import java.time.LocalDateTime;
import java.time.YearMonth;
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
public class CartaoDTO {
  private String numero;
  private String cvv;
  private String titular;
  private String dataValidade;
  private String bandeira;
  private String tipoCartao;
  private String cpf;
}
