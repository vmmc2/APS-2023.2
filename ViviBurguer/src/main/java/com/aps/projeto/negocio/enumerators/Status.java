package com.aps.projeto.negocio.enumerators;

import com.aps.projeto.negocio.converter.StatusConverter;

public enum Status {
  OK("OK"),
  INTERNAL_ERROR("Internal Server Error"),
  FORBIDDEN("Forbidden"),
  NOT_FOUND("Not Found"),
  BAD_REQUEST("Bad Request");
  public final String message;
  Status(String message) {
    this.message = message;
  }
  @Override
  public String toString() {
    return message;
  }
}
