package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for default page.
 */
@Controller
public class DefaultPageController {

    /**
     * Redirect to default page.
     * @return departments.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public final String defaultPageRedirect() {
        return "redirect:departments";
    }

}
