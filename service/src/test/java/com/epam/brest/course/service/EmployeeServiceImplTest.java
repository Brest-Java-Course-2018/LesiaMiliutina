package com.epam.brest.course.service;

import com.epam.brest.course.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-test.xml",
        "classpath:test-db-spring.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class EmployeeServiceImplTest {


    private static final String LANDAU = "Lev Landau";
    private static final String MARIE_CURIE = "Marie Curie";
    private static final String PIERRE_CURIE = "Pierre Curie";
    private static final String MAIL = "test@mail";
    private static final int SALARY_1 = 90000;
    private static final int SALARY_2 = 121000;
    private static final int D_ID = 1;


    @Autowired
    private EmployeeService employeeService;


    @Test
    public void getEmployees() {
        Collection<Employee> employees = employeeService.getEmployees();
        Assert.assertNotNull(employees);
        Assert.assertFalse(employees.isEmpty());
    }

    @Test
    public void getEmployeeById() {
        Employee test = new Employee(MARIE_CURIE, MAIL, SALARY_1,D_ID);
        employeeService.addEmployee(test);
        Employee employee = employeeService.getEmployeeById(test.getEmployeeId());
        Assert.assertNotNull(employee);
        Assert.assertEquals(MARIE_CURIE, employee.getEmployeeName());
        Assert.assertTrue(SALARY_1 == employee.getSalary());
        Assert.assertTrue(D_ID == employee.getDepartmentId());

    }

    @Test
    public void getEmployeesByDepartmentId() {
        int sizeBefore = employeeService.getEmployeesByDepartmentId(D_ID).size();
        employeeService.addEmployee(new Employee(MARIE_CURIE,MAIL,SALARY_1,D_ID));
        employeeService.addEmployee(new Employee(PIERRE_CURIE,MAIL,SALARY_1,D_ID));
        int sizeAfter = employeeService.getEmployeesByDepartmentId(D_ID).size();
        Assert.assertTrue((sizeAfter - sizeBefore) == 2);
    }

    @Test
    public void addEmployee() {
        Collection<Employee> employees = employeeService.getEmployees();
        int sizeBefore = employees.size();
        employeeService.addEmployee(new Employee(LANDAU,MAIL,SALARY_1, D_ID));
        employees = employeeService.getEmployees();
        Assert.assertEquals((sizeBefore + 1), employees.size());
    }

    @Test
    public void updateEmployee() {
        Employee employee =
                new Employee(MARIE_CURIE,MAIL,SALARY_1, 1);
        Employee newEmployee = employeeService.addEmployee(employee);
        newEmployee.setEmployeeName(LANDAU);
        newEmployee.setSalary(SALARY_2);
        employeeService.updateEmployee(newEmployee);
        Employee updated = employeeService.
                getEmployeeById(newEmployee.getEmployeeId());
        Assert.assertEquals(LANDAU, updated.getEmployeeName());
        Assert.assertTrue(SALARY_2 == updated.getSalary());
    }

    @Test
    public void deleteEmployeeById() {
        Employee employee = new Employee(MARIE_CURIE,MAIL,SALARY_2, D_ID);
        employeeService.addEmployee(employee);
        Collection<Employee> employees = employeeService.getEmployees();
        int sizeBefore = employees.size();
        employeeService.deleteEmployeeById(employee.getEmployeeId());
        employees = employeeService.getEmployees();
        Assert.assertEquals((sizeBefore - 1), employees.size());
    }
}