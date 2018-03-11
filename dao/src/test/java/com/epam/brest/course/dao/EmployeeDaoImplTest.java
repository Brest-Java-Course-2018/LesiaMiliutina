package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:test-dao.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class EmployeeDaoImplTest {

    public static final String LANDAU = "Lev Landau";
    public static final String PIERRE_CURIE = "Pierre Curie";
    public static final String MARIE_CURIE = "Marie Curie";
    public static final int SALARY_1 = 90000;
    public static final int SALARY_2 = 121000;
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void getEmployees() {
        List<Employee> employees = employeeDao.getEmployees();
        Assert.assertFalse(employees.isEmpty());
    }

    @Test
    public void getEmployeeById() {

        Employee employee = employeeDao.getEmployeeById(1);
        Assert.assertNotNull(employee);
        Assert.assertTrue(employee.getEmployeeId().equals(1));
        Assert.assertTrue(employee.getEmployeeName().equals(LANDAU));
        Assert.assertTrue(employee.getSalary().equals(SALARY_1));
        Assert.assertTrue(employee.getDepartmentId().equals(1));

    }

    @Test
    public void getEmployeesByDepartmentId() throws Exception {
        List<Employee> employees = employeeDao.getEmployeesByDepartmentId(1);
        int sizeBefore = employees.size();
        Employee employee1 = new Employee(PIERRE_CURIE, SALARY_1, 1);
        employeeDao.addEmployee(employee1);

        Employee employee2 = new Employee(MARIE_CURIE, SALARY_1, 1);
        employeeDao.addEmployee(employee2);
        employees = employeeDao.getEmployeesByDepartmentId(1);
        Assert.assertNotNull(employeeDao);
        Assert.assertTrue((employees.size() - sizeBefore) == 2);
    }


    @Test
    public void addEmployee() {
        List<Employee> employees = employeeDao.getEmployees();
        int sizeBefore = employees.size();
        Employee employee =
                new Employee(MARIE_CURIE, SALARY_1, 1);
        Employee newEmployee = employeeDao.addEmployee(employee);

        Assert.assertNotNull(newEmployee.getDepartmentId());
        Assert.assertTrue(newEmployee.getEmployeeName().
                equals(employee.getEmployeeName()));
        Assert.assertTrue(newEmployee.getSalary().
                equals(employee.getSalary()));
        Assert.assertTrue(
                (sizeBefore + 1) == employeeDao.getEmployees().size());
    }

    @Test
    public void updateEmployee() {

        Employee employee =
                new Employee(MARIE_CURIE, SALARY_1, 1);
        Employee newEmployee = employeeDao.addEmployee(employee);

        newEmployee.setEmployeeName(PIERRE_CURIE);
        newEmployee.setSalary(SALARY_2);
        employeeDao.updateEmployee(newEmployee);
        Employee updatedEmployee =
                employeeDao.getEmployeeById(newEmployee.getEmployeeId());
        Assert.assertTrue(newEmployee.getEmployeeId().
                equals(updatedEmployee.getEmployeeId()));
        Assert.assertTrue(newEmployee.getEmployeeName().
                equals(updatedEmployee.getEmployeeName()));
        Assert.assertTrue(newEmployee.getSalary().
                equals(updatedEmployee.getSalary()));
        Assert.assertTrue(newEmployee.getDepartmentId().
                equals(updatedEmployee.getDepartmentId()));

    }

    @Test
    public void deleteEmployeeById() {
        Employee employee =
                new Employee(MARIE_CURIE, SALARY_1, 1);
        employee = employeeDao.addEmployee(employee);
        List<Employee> employees = employeeDao.getEmployees();
        int sizeBefore = employees.size();
        employeeDao.deleteEmployeeById(employee.getEmployeeId());
        Assert.assertTrue((sizeBefore - 1) ==
                employeeDao.getEmployees().size());
    }
}