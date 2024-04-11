package pousada.sistemareserva.login;

public class tokenInvalidoException extends RuntimeException {
    public tokenInvalidoException(String message) {
        super(message);
    }
}
