package com.epam.brest.course.model;

import org.junit.Assert;

public class DepartmentTest {

    public static final String NAME = "Test dept";
    public static final String DESC = "Test desc";
    public static final Integer ID = 1;


    @org.junit.Test
    public void getDepartmentName() {
        Department department = new Department();
        department.setDepartmentName(NAME);
        Assert.assertTrue(department.getDepartmentName().equals(NAME));
    }

    @org.junit.Test
    public void getDepartmentId() {
        Department department = new Department();
        department.setDepartmentId(ID);
        Assert.assertTrue(department.getDepartmentId().equals(ID));
    }

    @org.junit.Test
    public void getDescription() {
        Department department = new Department();
        department.setDescription(DESC);
        Assert.assertTrue(department.getDescription().equals(DESC));
    }

}
