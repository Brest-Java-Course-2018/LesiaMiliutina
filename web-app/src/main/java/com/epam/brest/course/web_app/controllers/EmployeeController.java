package com.epam.brest.course.web_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

    /**
     * Edit employee page.
     * @param model employee model.
     * @return employee.
     */
    @GetMapping(value = "/employee")
    public final String employeePage(final Model model) {
        return "employee";
    }

    /**
     * Employees page.
     * @param model employees model.
     * @return employees.
     */
    @GetMapping(value = "/employees")
    public final String employeesPage(final Model model) {
        return "employees";
    }

}
