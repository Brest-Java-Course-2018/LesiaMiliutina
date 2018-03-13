package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for default page.
 */
@Controller
public class DefaultPageController {

    /**
     * Redirect to default page.
     * @return departments.
     */
    @GetMapping(value = "/")
    public final String defaultPageRedirect() {
        return "redirect:departments";
    }

}
