package exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
