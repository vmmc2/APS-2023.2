package com.aps.projeto.negocio.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compra {
  private Cartao cartao;
  private Carrinho carrinho;
}
