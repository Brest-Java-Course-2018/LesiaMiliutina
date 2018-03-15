package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;
    /**
     * Edit department controller.
     * @param id id of department.
     * @param model model.
     * @return view.
     */
    @GetMapping(value = "/department/{id}")
    public final String getDepartmentById(@PathVariable Integer id,
                                          Model model) {
        Department department =
                departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        return "department";
    }

    /**
     * Departments page.
     * @param model departments model.
     * @return departments.
     */
    @GetMapping(value = "/departments")
    public final String getDepartments(final Model model) {
        Collection<Department> departments =
                departmentService.getDepartments();
        model.addAttribute("departments", departments);
        return "departments";
    }
}
