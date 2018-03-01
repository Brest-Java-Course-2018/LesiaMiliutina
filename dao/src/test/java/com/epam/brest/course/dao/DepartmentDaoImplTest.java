package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-db-spring.xml",
        "classpath:test-dao.xml"})
public class DepartmentDaoImplTest {

  @Autowired
  DepartmentDao departmentDao;

  @Test
  public void getDepartments() {
    List<Department> departments = departmentDao.getDepartments();
    Assert.assertFalse(departments.isEmpty());

  }

  @Test
  public void getDepartmentById() {
    Department department = departmentDao.getDepartmentById(1);
    Assert.assertNotNull(department);
    Assert.assertTrue(department.getDepartmentId().equals(1));
    Assert.assertTrue(department.getDepartmentName().equals("Distribution"));
    Assert.assertTrue(department.getDescription().equals("Distribution Department"));
  }

  @Test
  public void addDepartment() {
    Department testDepartment = new Department();
    testDepartment.setDepartmentName("Test department");
    testDepartment.setDescription("Test description");
    Department department = departmentDao.addDepartment(testDepartment);
    Assert.assertNotNull(department);
    Assert.assertTrue(department.getDepartmentName().equals("Test department"));
    Assert.assertTrue(department.getDescription().equals("Test description"));
  }

  @Test
  public void updateDepartment() {
    Department testDepartment = new Department();
    testDepartment.setDepartmentId(1);
    testDepartment.setDepartmentName("Test name");
    testDepartment.setDescription("Test description");
    departmentDao.updateDepartment(testDepartment);
    Assert.assertTrue(testDepartment.getDepartmentName().equals("Test name"));
    Assert.assertTrue(testDepartment.getDescription().equals("Test description"));

  }

  @Test(expected = EmptyResultDataAccessException.class)
  public void deleteDepartmentById() {
    Department department = new Department();
    department.setDepartmentId(88);
    departmentDao.addDepartment(department);
    departmentDao.deleteDepartmentById(88);
    departmentDao.getDepartmentById(88);
  }

}