package com.epam.brest.course.client.rest;

import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context-test.xml")
public class DepartmentServiceRestMockTest {

    private static DepartmentDto departmentDto1;
    private static DepartmentDto departmentDto2;
    private static Department department;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RestTemplate mockRestTemplate;

    @Before
    public void setUp() {
        departmentDto1 = new DepartmentDto();
        departmentDto1.setDepartmentId(1);
        departmentDto1.setDepartmentName("name1");

        departmentDto2 = new DepartmentDto();
        departmentDto2.setDepartmentId(2);
        departmentDto2.setDepartmentName("name2");

        department = new Department("name","desc");
        department.setDepartmentId(3);
    }

    @After
    public void tearDown() {
        verify(mockRestTemplate);
        reset(mockRestTemplate);
    }

    @Test
    public void getDepartmentsDto() {
        List departments = Arrays.asList(departmentDto1, departmentDto2);
        ResponseEntity responseEntity =
                new ResponseEntity(departments, HttpStatus.OK);
        expect(mockRestTemplate.getForEntity(anyString(), anyObject()))
                .andReturn(responseEntity);
        replay(mockRestTemplate);

        Collection<DepartmentDto> results =
                departmentService.getDepartmentsDto();

        Assert.assertNotNull(departments);
        Assert.assertEquals(2, departments.size());
    }

    @Test
    public void getDepartmentById() {
        ResponseEntity responseEntity =
                new ResponseEntity(department, HttpStatus.OK);
        expect(mockRestTemplate.getForEntity(anyString(),anyObject()))
                .andReturn(responseEntity);
        replay(mockRestTemplate);

        Department result = departmentService.getDepartmentById(3);
        Assert.assertNotNull(result);
        Assert.assertEquals("name", result.getDepartmentName());
    }

    @Test
    public void addDepartment() {
        ResponseEntity responseEntity =
                new ResponseEntity(department, HttpStatus.OK);
        expect(mockRestTemplate.postForEntity(anyString(),anyObject(),anyObject()))
                .andReturn(responseEntity);
        replay(mockRestTemplate);

        Department result = departmentService.addDepartment(department);
        Assert.assertNotNull(result);
        Assert.assertEquals("name", result.getDepartmentName());
        Assert.assertEquals("desc", result.getDescription());
        Assert.assertEquals(3, result.getDepartmentId().intValue());
    }
}
