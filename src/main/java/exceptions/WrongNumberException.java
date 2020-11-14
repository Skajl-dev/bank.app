package exceptions;

public class WrongNumberException extends RuntimeException {
    public WrongNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongNumberException(String message) {
        super(message);
    }
}
