package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    /**
     * Edit department page.
     * @param model department model.
     * @return department.
     */
    @GetMapping(value = "/department")
    public final String departmentPage(final Model model) {
        return "department";
    }

    /**
     * Departments page.
     * @param model departments model.
     * @return departments.
     */
    @GetMapping(value = "/departments")
    public final String departmentsPage(final Model model) {
        return "departments";
    }
}
