package br.com.viviburguer.accountregister.negocio.exceptions;

public class BadRequestException extends RuntimeException {

    static final long serialVersionUID = -7034892190745766939L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }
}
