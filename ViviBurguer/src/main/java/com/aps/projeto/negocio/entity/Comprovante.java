package com.aps.projeto.negocio.entity;

import com.aps.projeto.negocio.enumerators.Status;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comprovante {
  private UUID id;
  private String message;
  private Status status;
}
