package model.exception;

public class RateCalculateException extends RuntimeException {
    RateCalculateException() {
        super();
    }

    public RateCalculateException(String message) {
        super(message);
    }
}
