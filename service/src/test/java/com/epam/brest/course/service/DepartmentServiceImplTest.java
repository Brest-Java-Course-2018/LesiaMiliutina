package com.epam.brest.course.service;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:service-test.xml",
        "classpath:test-db-spring.xml", "classpath:dao.xml"})
@Rollback
@Transactional
public class DepartmentServiceImplTest {

    private static final int ID = 1;
    private static final String DEPT_NAME = "Test name";
    private static final String DESC = "Test description";
    private static final String NEW_NAME = "New name";
    private static final String NEW_DESC = "New description";
    public static final String
            DISTRIBUTION = "Distribution";
    public static final String
            DISTRIBUTION_DEPARTMENT = "Distribution Department";

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void getDepartments() {
        Collection<Department> departments =
                departmentService.getDepartments();
        Assert.assertNotNull(departments);
        Assert.assertFalse(departments.isEmpty());
    }

    @Test
    public void getDepartmentById() {
        Department department =
                departmentService.getDepartmentById(ID);
        Assert.assertNotNull(department);
        Assert.assertEquals(DISTRIBUTION, department.getDepartmentName());
        Assert.assertEquals(DISTRIBUTION_DEPARTMENT, department.getDescription());
    }

    @Test
    public void addDepartment() {
       Collection<Department> departments = departmentService.getDepartments();
       int sizeBefore = departments.size();
       departmentService.addDepartment(new Department());
       departments = departmentService.getDepartments();
       Assert.assertEquals((sizeBefore + 1), departments.size());
    }

    @Test
    public void updateDepartment() {
        Department department = new Department(DEPT_NAME, DESC);
        Department newDepartment = departmentService.addDepartment(department);
        newDepartment.setDepartmentName(NEW_NAME);
        newDepartment.setDescription(NEW_DESC);
        departmentService.updateDepartment(newDepartment);
        Department updated = departmentService.
                getDepartmentById(newDepartment.getDepartmentId());
        Assert.assertEquals(NEW_NAME, updated.getDepartmentName());
        Assert.assertEquals(NEW_DESC, updated.getDescription());

    }

    @Test
    public void deleteDepartmentById() {
        Department department = new Department(DEPT_NAME, DESC);
        departmentService.addDepartment(department);
        Collection<Department> departments = departmentService.getDepartments();
        int sizeBefore = departments.size();
        departmentService.deleteDepartmentById(department.getDepartmentId());
        departments = departmentService.getDepartments();
        Assert.assertEquals((sizeBefore - 1), departments.size());

    }

    @Test
    public void updateDepartmentDescription() {
        departmentService.updateDepartmentDescription(ID, DESC);

        Department department =
                departmentService.getDepartmentById(ID);
        Assert.assertEquals(DESC, department.getDescription());
    }

}