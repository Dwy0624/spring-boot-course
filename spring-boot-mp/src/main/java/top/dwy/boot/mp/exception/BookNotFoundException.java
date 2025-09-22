package top.dwy.boot.mp.exception;

/**
 * @author alani
 */
public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String message) {
        super(message);
    }
}
