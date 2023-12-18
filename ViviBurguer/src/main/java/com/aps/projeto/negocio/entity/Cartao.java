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
  @Column(unique = true)
  @Convert(converter = CPFConverter.class)
  private CPF cpf;
}
