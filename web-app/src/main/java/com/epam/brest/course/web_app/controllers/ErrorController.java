package com.epam.brest.course.web_app.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Controller for error page.
 */
@ControllerAdvice
public class ErrorController extends RuntimeException {

    /**
     * Handling an exception.
     * @param model model.
     * @param e exception.
     *
     * @return departments.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public final String handleException(final Model model,
                                        final IllegalArgumentException e) {
        model.addAttribute("message", e.getMessage());
        return "errorPages/illArgExc";
    }


}
