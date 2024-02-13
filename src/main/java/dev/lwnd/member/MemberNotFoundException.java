package dev.lwnd.member;

/**
 * This exception is thrown when a member is not found in the system.
 */
public class MemberNotFoundException extends RuntimeException {

    /**
     * Constructs a new MemberNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public MemberNotFoundException(String message) {
        super(message);
    }
}
