package com.aps.projeto.negocio.entity;

import java.util.UUID;
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
public class Comprovante {
  private UUID id;
  private String message;
  private String status;
}
