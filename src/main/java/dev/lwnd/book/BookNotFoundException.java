package dev.lwnd.book;

/**
 * This exception is thrown when a book is not found in the library.
 */
public class BookNotFoundException extends RuntimeException {
    /**
     * Constructs a new BookNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public BookNotFoundException(String message) {
        super(message);
    }
}
