package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementation of DepartmentService interface.
 */
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
