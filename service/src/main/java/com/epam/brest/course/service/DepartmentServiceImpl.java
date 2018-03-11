package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
//import com.epam.brest.course.dao.EmployeeDao;
import com.epam.brest.course.model.Department;
//import com.epam.brest.course.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

//import java.util.List;

/**
 * Implementation of DepartmentService interface.
 */
public class DepartmentServiceImpl implements DepartmentService {

    /**
     * Logger for DepartmentServiceImpl class.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Marker for disbanded departments.
     */
//     public static final String MARKER = "Department is disbanded!";

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

//    private EmployeeDao employeeDao;

    /**
     * The method needs to be corrected!!!
     * find out why getDepartmentId returns null
     * solve problem with employeeDao
     *
     * Mark disbanded departments.
     */
    @Override
    public void markDepartmentsAsDisbanded() {
//        LOGGER.debug("markDisbandedDepartment()");
//
//        List<Department> allDepartments = departmentDao.getDepartments();
//
//        for (Department department: allDepartments) {
//            List<Employee> employees = employeeDao.
//                    getEmployeesByDepartmentId(department.getDepartmentId());
//            if (employees.size() == 0) {
//                department.setDescription(MARKER);
//                departmentDao.updateDepartment(department);
//            }
//        }
   }

}
