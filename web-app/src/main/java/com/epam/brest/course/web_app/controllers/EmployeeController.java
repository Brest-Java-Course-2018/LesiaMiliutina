package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

    /**
     * Employee service field for employee controller.
     */
    @Autowired
    private EmployeeService employeeService;

    /**
     * Goto edit employee page.
     * @param id id of employee.
     * @param model employee model.
     * @return employee.
     */
    @GetMapping(value = "/employee/{id}")
    public final String getEmployeeById(@PathVariable final Integer id,
                                        final Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("header", "edit employee");
        return "employee";
    }

    /**
     * Goto add form.
     * @param model model.
     * @return view.
     */
    @GetMapping(value = "/addEmployee")
    public final String addEmployee(final Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("header", "add employee");
        return "employee";
    }

    /**
     * Employees page.
     * @param model employees model.
     * @return employees.
     */
    @GetMapping(value = "/employees")
    public final String employeesPage(final Model model) {
        Collection<Employee> employees =
                employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

}
