package com.epam.brest.course.client;

/**
 * Data access exception.
 */
public class ServerDataAccessException extends RuntimeException {
    /**
     * Constructor with a parameter.
     * @param message error message.
     */
    public ServerDataAccessException(String message) {
        super(message);
    }
}
