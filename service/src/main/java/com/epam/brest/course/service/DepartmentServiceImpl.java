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

    @Autowired
    private DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao deptDao) {
        this.departmentDao = deptDao;
    }

    @Override
    public Department getDepartmentById(Integer departmentId) {
        LOGGER.debug("getDepartmentById({})", departmentId);
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public void updateDepartmentDescription
            (Integer departmentId, String description) {

        LOGGER.debug("updateDepartmentDescription({}, {})",
                departmentId, description);

        Department department = departmentDao.getDepartmentById(departmentId);
        department.setDescription(description);
        departmentDao.updateDepartment(department);
    }
}
