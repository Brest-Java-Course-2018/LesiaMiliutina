package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.List;

public class DepartmentConsumerRest implements DepartmentService {

    private static final Logger LOGGER = LogManager.getLogger();

    private String url;

    private RestTemplate restTemplate;

    public DepartmentConsumerRest(final String url,
                                  final RestTemplate restTemplate) {
        this.url = url;
        this.restTemplate = restTemplate;
    }

    /**
     * Method for getting all rows of table.
     *
     * @return all departments.
     */
    @Override
    public Collection<Department> getDepartments() {
        return null;
    }

    /**
     * Method for getting a department.
     *
     * @param departmentId id of department.
     * @return department by its id.
     */
    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        ResponseEntity<Department> responseEntity =
                restTemplate.getForEntity(url + "/" + departmentId,
                        Department.class);
        Department result = responseEntity.getBody();
        LOGGER.debug("getDepartmentById: result({})", result);
        return result;

    }

    /**
     * Method for adding a department.
     *
     * @param department department for adding.
     * @return added department.
     */
    @Override
    public Department addDepartment(Department department) {
        LOGGER.debug("addDepartment({})", department);
        ResponseEntity<Department> responseEntity =
                restTemplate.postForEntity(url, department, Department.class);
        Department result = responseEntity.getBody();
        LOGGER.debug("addDepartment: result({})", result);
        return result;
    }

    /**
     * Method for updating.
     *
     * @param department department for updating.
     */
    @Override
    public void updateDepartment(Department department) {

    }

    /**
     * Method for deleting.
     *
     * @param id id of department to remove.
     */
    @Override
    public void deleteDepartmentById(Integer id) {

    }

    /**
     * Method for updating department description.
     *
     * @param departmentId id of department.
     * @param description  new description.
     */
    @Override
    public void updateDepartmentDescription(Integer departmentId,
                                            String description) {

    }

    /**
     * Get all departments.
     *
     * @return collection.
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection<DepartmentDto> getDepartmentsDto() {
        LOGGER.debug("getDepartmentsDto()");
        ResponseEntity responseEntity =
                restTemplate.getForEntity(url, List.class);
        List<DepartmentDto> departments =
                (List<DepartmentDto>) responseEntity.getBody();
        return departments;
    }
}
