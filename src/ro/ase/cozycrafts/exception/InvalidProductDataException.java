package ro.ase.cozycrafts.exception;

public class InvalidProductDataException extends Exception {
    public InvalidProductDataException(String message) {
        super(message);
    }

    public InvalidProductDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
