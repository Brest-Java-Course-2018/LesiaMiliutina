package com.epam.brest.course.service;

import com.epam.brest.course.dao.DepartmentDao;
import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.model.Department;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-mock-test.xml"})
public class DepartmentServiceImplMockTest {

    private static final int ID = 1;
    private static final String DESC = "Academic department";

    @TestSubject
    private static final Department DEPARTMENT =
            new Department("Distribution", "Distribution Department");


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentDao mockDepartmentDao;

    @Test
    public void getDepartments() {
        Collection<Department> departments =
                new ArrayList<>();

        EasyMock.expect(mockDepartmentDao.
                getDepartments()).andReturn(departments);
        EasyMock.replay(mockDepartmentDao);

        departmentService.getDepartments();
        EasyMock.verify(mockDepartmentDao);

        EasyMock.reset(mockDepartmentDao);
    }

    @Test
    public void getDepartmentById() {

        EasyMock.expect(mockDepartmentDao.
                getDepartmentById(ID)).andReturn(DEPARTMENT);
        EasyMock.replay(mockDepartmentDao);

        departmentService.getDepartmentById(ID);
        EasyMock.verify(mockDepartmentDao);

        EasyMock.reset(mockDepartmentDao);
    }

    @Test
    public void addDepartment() {
        EasyMock.expect(mockDepartmentDao.
                addDepartment(DEPARTMENT)).andReturn(DEPARTMENT);
        EasyMock.replay(mockDepartmentDao);

        departmentService.addDepartment(DEPARTMENT);
        EasyMock.verify(mockDepartmentDao);

        EasyMock.reset(mockDepartmentDao);
    }

    @Test
    public void updateDepartment() {
        mockDepartmentDao.updateDepartment(DEPARTMENT);
        EasyMock.expectLastCall();

        EasyMock.replay(mockDepartmentDao);

        departmentService.updateDepartment(DEPARTMENT);
        EasyMock.verify(mockDepartmentDao);

        EasyMock.reset(mockDepartmentDao);

    }

    @Test
    public void deleteDepartmentById() {
        mockDepartmentDao.deleteDepartmentById(ID);
        EasyMock.expectLastCall();

        EasyMock.replay(mockDepartmentDao);

        departmentService.deleteDepartmentById(ID);
        EasyMock.verify(mockDepartmentDao);

        EasyMock.reset(mockDepartmentDao);
    }

    @Test
    public void updateDepartmentDescription() {

        EasyMock.expect(mockDepartmentDao.
                getDepartmentById(EasyMock.anyInt())).andReturn(DEPARTMENT);
        Capture<Department> captureArgument = Capture.newInstance();
        mockDepartmentDao.updateDepartment(EasyMock.capture(captureArgument));
        EasyMock.expectLastCall();
        EasyMock.replay(mockDepartmentDao);

        departmentService.updateDepartmentDescription(ID, DESC);

        Department department = captureArgument.getValue();
        Assert.assertEquals(DESC, department.getDescription());
    }

    @Test
    public void getDepartmentsDto() {
        Collection<DepartmentDto> departments =
                new ArrayList<>();

        EasyMock.expect(mockDepartmentDao.
                getDepartmentsDto()).andReturn(departments);
        EasyMock.replay(mockDepartmentDao);

        departmentService.getDepartmentsDto();
        EasyMock.verify(mockDepartmentDao);

        EasyMock.reset(mockDepartmentDao);

    }

}