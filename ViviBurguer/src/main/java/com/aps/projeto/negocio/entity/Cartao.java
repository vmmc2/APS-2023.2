package com.aps.projeto.negocio.entity;

import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cartao {
  private String numero;
  private String cvv;
  private String titular;
  private LocalDateTime dataValidade;
  private String bandeira;
  private String tipoCartao;
  private CPF cpf;
}
