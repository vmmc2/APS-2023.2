package com.aps.projeto.negocio.pojos;

import com.aps.projeto.negocio.enumerators.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
@Accessors(chain = true)
@Getter
@Setter
@AllArgsConstructor
public class BasicResponse {
  private String message;
  private Status status;
}
