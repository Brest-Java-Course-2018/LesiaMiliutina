package com.epam.brest.course.web_app.controllers;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Department controller.
 */
@Controller
public class DepartmentController {

    /**
     * Logger for department controller.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * DepartmentService field for department controller.
     */
    @Autowired
    private DepartmentService departmentService;

    /**
     * Goto departments page.
     * @param model departments model.
     * @return departments.
     */
    @GetMapping(value = "/departments")
    public final String getDepartments(final Model model) {
        LOGGER.debug("getDepartments({})", model);
        Collection<DepartmentDto> departmentDtoCollection =
                departmentService.getDepartmentsDto();
        model.addAttribute("departments", departmentDtoCollection);
        return "departments";
    }


    /**
     * Goto edit department page.
     * @param id id of department.
     * @param model model.
     * @return view.
     */
    @GetMapping(value = "/department/{id}")
    public final String editDepartmentPage(@PathVariable final Integer id,
                                          final Model model) {
        LOGGER.debug("editDepartmentPage({},{})", id, model);
        Department department =
                departmentService.getDepartmentById(id);
        model.addAttribute("department", department);
        model.addAttribute("isNew", false);
        return "department";
    }

    /**
     * Update department.
     * @param department updated department.
     * @param result binding result.
     * @return view.
     */
    @PostMapping(value = "/department/{id}")
    public final String editDepartment(@Valid final Department department,
                                             final BindingResult result) {
        LOGGER.debug("updateDepartment({}, {})", department, result);
        if (result.hasErrors()) {
            return "department";
        } else {
            this.departmentService.updateDepartment(department);
            return "redirect:/departments";
        }
    }

    /**
     * Goto new department page.
     * @param model model.
     * @return view.
     */
    @GetMapping(value = "/department")
    public final String addDepartmentPage(final Model model) {
        LOGGER.debug("addDepartmentPage({})", model);
        Department department = new Department();
        model.addAttribute("department", department);
        model.addAttribute("isNew", true);
        return "department";
    }

    /**
     * Add department.
     * @param department department object for adding.
     * @param result binding result.
     * @return view.
     */
    @PostMapping(value = "/department")
    public final String addDepartment(
            @Valid  final Department department, final BindingResult result) {
        LOGGER.debug("addDepartment({},{})", department, result);
        if (result.hasErrors()) {
            return "department";
        } else {
            departmentService.addDepartment(department);
            return "redirect:/departments";
        }
    }

    /**
     * Delete department.
     * @param id id of department.
     * @param model model.
     * @return view.
     */
    @GetMapping(value = "/department/{id}/delete")
    public final String deleteDepartment(@PathVariable final Integer id,
                                         final Model model) {
        LOGGER.debug("deleteDepartment({},{})", id, model);
        departmentService.deleteDepartmentById(id);
        return "redirect:/departments";
    }

}
