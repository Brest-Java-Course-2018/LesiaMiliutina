package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.model.Employee;
import com.epam.brest.course.service.DepartmentService;
import com.epam.brest.course.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

/**
 * Employee controller.
 */
@Controller
public class EmployeeController {

    /**
     * Logger for employee controller.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Employee service field for employee controller.
     */
    @Autowired
    private EmployeeService employeeService;


    /**
     * Department service field for employee controller.
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     *  Goto employees page.
     * @param model employees model.
     * @return employees.
     */
    @GetMapping(value = "/employees")
    public final String getEmployees(final Model model) {
        LOGGER.debug("getDepartments({})", model);
        Collection<Employee> employees =
                employeeService.getEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    /**
     * Goto edit employee page.
     * @param id id of employee.
     * @param model employee model.
     * @return employee.
     */
    @GetMapping(value = "/employee/{id}")
    public final String editEmployeePage(@PathVariable final Integer id,
                                        final Model model) {
        LOGGER.debug("editEmployeePage({},{})", id, model);
        Collection<DepartmentDto> departments =
                departmentService.getDepartmentsDto();
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        model.addAttribute("isNew", false);
        return "employee";
    }

    /**
     * Update employee.
     * @param employee updated employee.
     * @param result binding result.
     * @return view.
     */
    @PostMapping(value = "/employee/{id}")
    public final String editEmployee(/*@Valid*/final Employee employee,
                                           final BindingResult result) {
        LOGGER.debug("updateEmployee({}, {})", employee, result);
        if (result.hasErrors()) {
            return "/employee";
        } else {
            this.employeeService.updateEmployee(employee);
            return "redirect:/employees";
        }
    }

    /**
     * Goto new employee page.
     * @param model model.
     * @return view.
     */
    @GetMapping(value = "/employee")
    public final String addEmployeePage(final Model model) {
        LOGGER.debug("addEmployeePage({})", model);
        Collection<DepartmentDto> departments =
                departmentService.getDepartmentsDto();
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        model.addAttribute("isNew", true);
        return "employee";
    }

    /**
     * Add employee.
     * @param employee employee object for adding.
     * @param result binding result.
     * @return view.
     */
    @PostMapping(value = "/employee")
    public final String addEmployee(
            final Employee employee, final BindingResult result) {
        LOGGER.debug("addEmployee({},{})", employee, result);
        if (result.hasErrors()) {
            return "/employee";
        } else {
            employeeService.addEmployee(employee);
            return "redirect:/employees";
        }
    }

    /**
     * Delete employee.
     * @param id id of employee.
     * @param model model.
     * @return view.
     */
    @GetMapping(value = "/employee/{id}/delete")
    public final String deleteEmployee(@PathVariable final Integer id,
                                         final Model model) {
        LOGGER.debug("deleteEmployee({},{})", id, model);
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }

}
