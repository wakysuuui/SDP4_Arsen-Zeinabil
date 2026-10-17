package robots;

public class DeliveryException extends RuntimeException {
    public DeliveryException(String message, Throwable cause) {
        super(message, cause);
    }
}