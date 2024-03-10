package br.com.viviburguer.common.negocio.enumerators;

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
