package br.com.viviburguer.common.negocio.converter;

import br.com.viviburguer.common.negocio.enumerators.Status;
import org.springframework.http.HttpStatus;

public class StatusConverter {

  public static HttpStatus toHttpStatus(Status status) {
    return switch (status) {
      case OK -> HttpStatus.OK;
      case INTERNAL_ERROR -> HttpStatus.INTERNAL_SERVER_ERROR;
      case FORBIDDEN -> HttpStatus.FORBIDDEN;
      case NOT_FOUND -> HttpStatus.NOT_FOUND;
      case BAD_REQUEST -> HttpStatus.BAD_REQUEST;
      default -> throw new IllegalArgumentException(
          "Conversão para HttpStatus não suportada para o status: " + status);
    };
  }

  public static Status fromHttpStatus(HttpStatus httpStatus) {
    return switch (httpStatus) {
      case OK -> Status.OK;
      case INTERNAL_SERVER_ERROR -> Status.INTERNAL_ERROR;
      case FORBIDDEN -> Status.FORBIDDEN;
      case NOT_FOUND -> Status.NOT_FOUND;
      case BAD_REQUEST -> Status.BAD_REQUEST;
      default -> throw new IllegalArgumentException(
          "Conversão de HttpStatus para Status não suportada para o status: " + httpStatus);
    };
  }
}