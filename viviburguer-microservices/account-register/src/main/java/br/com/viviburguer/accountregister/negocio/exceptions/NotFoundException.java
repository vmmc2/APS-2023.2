package br.com.viviburguer.accountregister.negocio.exceptions;

public class NotFoundException extends RuntimeException {

    static final long serialVersionUID = -7134892190745716939L;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }
}