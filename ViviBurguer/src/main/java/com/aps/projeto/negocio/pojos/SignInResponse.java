package com.aps.projeto.negocio.pojos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Accessors(chain = true)
@Getter
@Setter
public class SignInResponse {
  String nome;
  String email;
  String message;
  HttpStatus status;

}
