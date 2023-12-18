package com.aps.projeto.negocio.entity;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
  private String nome;
  private BigDecimal preco;
  private BigInteger quantidade;
}
