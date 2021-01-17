package mate.academy.exception;

import java.sql.SQLException;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String warning, SQLException e) {
        super(warning, e);
    }
}
