package br.com.viviburguer.common.negocio.entity;

import br.com.viviburguer.common.negocio.enumerators.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comprovante {
  private UUID id;
  private String message;
  private Status status;
}
