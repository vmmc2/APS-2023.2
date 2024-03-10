package br.com.viviburguer.accountregister.negocio.exceptions;

public class ForbiddenException extends RuntimeException {

    static final long serialVersionUID = -7134892190745766939L;

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }
}
