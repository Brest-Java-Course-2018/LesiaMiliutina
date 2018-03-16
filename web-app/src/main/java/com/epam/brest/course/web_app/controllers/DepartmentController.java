package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.dto.DepartmentDto;
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

    /**
     * DepartmentService field for department controller.
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     * Goto edit department page.
     * @param id id of department.
     * @param model model.
     * @return view.
     */
    @GetMapping(value = "/department/{id}")
    public final String getDepartmentById(@PathVariable final Integer id,
                                          final Model model) {
        Department department =
                departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        model.addAttribute("header", "edit department");
        return "department";
    }

    /**
     * Goto add form.
     * @param model model.
     * @return view.
     */
    @GetMapping(value = "/addDepartment")
    public final String addDepartment(final Model model) {
        Department department = new Department();
        model.addAttribute("department", department);
        model.addAttribute("header", "add department");
        return "department";
    }

    /**
     * Goto departments page.
     * @param model departments model.
     * @return departments.
     */
    @GetMapping(value = "/departments")
    public final String getDepartments(final Model model) {
        Collection<DepartmentDto> departmentDtoCollection =
                departmentService.getAverageSalary();
        model.addAttribute("departments", departmentDtoCollection);
        return "departments";
    }
}
