package logica.validacion.exceptions;

public class ValidationException extends Exception { // Actualizacion

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
