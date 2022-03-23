package service.exception;

public class MortageException extends RuntimeException {
    public MortageException() {
        super("Case not handled");
    }

}
