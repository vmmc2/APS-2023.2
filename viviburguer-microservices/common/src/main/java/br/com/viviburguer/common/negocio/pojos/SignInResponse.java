package br.com.viviburguer.common.negocio.pojos;

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
  String cpf;
  String message;
  HttpStatus status;
}
