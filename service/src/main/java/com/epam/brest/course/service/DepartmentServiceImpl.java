package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.dto.DepartmentDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Implementation of DepartmentService interface.
 */
@Transactional
public class DepartmentServiceImpl implements DepartmentService {


    /**
     * Logger for DepartmentServiceImpl class.
     */
    private static final Logger LOGGER = LogManager.getLogger();


    /**
     * DepartmentDao object for services.
     */
    @Autowired
    private DepartmentDao departmentDao;


    /**
     * Constructor with parameter.
     * @param deptDao object of DepartmentDao class.
     */
    public DepartmentServiceImpl(final DepartmentDao deptDao) {
        this.departmentDao = deptDao;
    }


    /**
     * Method for getting all rows of table.
     *
     * @return all departments.
     */
    @Override
    public final Collection<Department> getDepartments() {
        LOGGER.debug("getDepartments()");
        return departmentDao.getDepartments();
    }

    /**
     * Method for getting a department.
     * @param departmentId id of department.
     * @return department by its id.
     */
    @Override
    public final Department getDepartmentById(
            final Integer departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }

    /**
     * Method for adding a department.
     *
     * @param department department for adding.
     * @return added department.
     */
    @Override
    public final Department addDepartment(final Department department) {
        LOGGER.debug("addDepartment({})", department);
        return departmentDao.addDepartment(department);
    }

    /**
     * Method for updating.
     *
     * @param department department for updating.
     */
    @Override
    public final void updateDepartment(final Department department) {
        LOGGER.debug("updateDepartment({})", department);
        departmentDao.updateDepartment(department);
    }

    /**
     * Method for deleting.
     *
     * @param id id of department to remove.
     */
    @Override
    public final void deleteDepartmentById(final Integer id) {
        LOGGER.debug("deleteDepartmentById({})", id);
        departmentDao.deleteDepartmentById(id);
    }


    /**
     * Returns average salary of each department.
     *
     * @return average salary.
     */
    @Override
    public final Collection<DepartmentDto> getDepartmentsDto() {
        LOGGER.debug("getDepartmentsDto");

        Collection<DepartmentDto> dto = departmentDao.getDepartmentsDto();
        return  dto;
    }

    /**
     * Method for updating department description.
     * @param departmentId id of department.
     * @param description new description.
     */
    @Override
    public final void updateDepartmentDescription(
            final Integer departmentId, final String description) {

        LOGGER.debug("updateDepartmentDescription({}, {})",
                departmentId, description);

        Department department = departmentDao.getDepartmentById(departmentId);
        department.setDescription(description);
        departmentDao.updateDepartment(department);
    }

}
