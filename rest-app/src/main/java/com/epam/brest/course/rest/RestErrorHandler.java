package com.epam.brest.course.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * For handling errors.
 */
@ControllerAdvice
public class RestErrorHandler {

    /**
     * Logger for RestErrorHandler class.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Handling DataAccessException.
     * @param ex exception.
     * @return message for user.
     */
    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final @ResponseBody String handleDataAccessException(
           final DataAccessException ex) {
        LOGGER.debug("rest: handleDataAccessException({})", ex);
        return "DataAccessException: " + ex.getLocalizedMessage();
    }
}
