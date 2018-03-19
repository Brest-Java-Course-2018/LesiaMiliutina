package com.epam.brest.course.service;

import com.epam.brest.course.dao.EmployeeDao;
import com.epam.brest.course.model.Employee;
import org.easymock.EasyMock;
import org.easymock.TestSubject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class EmployeeServiceImplMockTest {

    private static final int ID = 1;

    @TestSubject
    private static final Employee EMPLOYEE =
            new Employee("Lev Landau",
                    "llandau@mail", 90000, 1);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeDao mockEmployeeDao;

    @Test
    public void getEmployees() {
        Collection<Employee> employees =
                new ArrayList<>();

        EasyMock.expect(mockEmployeeDao.
                getEmployees()).andReturn(employees);
        EasyMock.replay(mockEmployeeDao);

        employeeService.getEmployees();
        EasyMock.verify(mockEmployeeDao);

        EasyMock.reset(mockEmployeeDao);
    }

    @Test
    public void getEmployeeById() {
        EasyMock.expect(mockEmployeeDao.
                getEmployeeById(ID)).andReturn(EMPLOYEE);
        EasyMock.replay(mockEmployeeDao);

        employeeService.getEmployeeById(ID);
        EasyMock.verify(mockEmployeeDao);

        EasyMock.reset(mockEmployeeDao);
    }

    @Test
    public void getEmployeesByDepartmentId() {
        Collection<Employee> employees =
                new ArrayList<>();

        EasyMock.expect(mockEmployeeDao.
                getEmployeesByDepartmentId(ID)).andReturn(employees);
        EasyMock.replay(mockEmployeeDao);

        employeeService.getEmployeesByDepartmentId(ID);
        EasyMock.verify(mockEmployeeDao);

        EasyMock.reset(mockEmployeeDao);
    }

    @Test
    public void addEmployee() {
        EasyMock.expect(mockEmployeeDao.
                addEmployee(EMPLOYEE)).andReturn(EMPLOYEE);
        EasyMock.replay(mockEmployeeDao);

        employeeService.addEmployee(EMPLOYEE);
        EasyMock.verify(mockEmployeeDao);

        EasyMock.reset(mockEmployeeDao);
    }

    @Test
    public void updateEmployee() {
        mockEmployeeDao.updateEmployee(EMPLOYEE);
        EasyMock.expectLastCall();

        EasyMock.replay(mockEmployeeDao);

        employeeService.updateEmployee(EMPLOYEE);
        EasyMock.verify(mockEmployeeDao);

        EasyMock.reset(mockEmployeeDao);

    }

    @Test
    public void deleteEmployeeById() {
        mockEmployeeDao.deleteEmployeeById(ID);
        EasyMock.expectLastCall();

        EasyMock.replay(mockEmployeeDao);

        employeeService.deleteEmployeeById(ID);
        EasyMock.verify(mockEmployeeDao);

        EasyMock.reset(mockEmployeeDao);
    }
}
