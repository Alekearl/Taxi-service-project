package mate.academy.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String warning, Throwable e) {
        super(warning, e);
    }
}
