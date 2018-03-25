package com.epam.brest.course.rest;

import com.epam.brest.course.dto.DepartmentDto;
import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-spring-test.xml"})
public class DepartmentControllerMockTest {

    private static final String D_NAME = "Test dept";
    private static final String D_DESC = "Test desc";
    private static final Integer D_ID = 1;
    private static DepartmentDto departmentDto1;
    private static DepartmentDto departmentDto2;

    @Autowired
    private DepartmentRestController departmentRestController;

    @Autowired
    private DepartmentService departmentService;

    private MockMvc mockMvc;



    @Before
    public void setUp() {
        departmentDto1 = new DepartmentDto();
        departmentDto1.setDepartmentId(1);
        departmentDto1.setDepartmentName("name1");

        departmentDto2 = new DepartmentDto();
        departmentDto2.setDepartmentId(2);
        departmentDto2.setDepartmentName("name2");

        mockMvc = MockMvcBuilders.standaloneSetup(departmentRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    @After
    public void tearDown() {
        verify(departmentService);
        reset(departmentService);
    }
    @Test
    public void getDepartments() throws Exception {

        expect(departmentService.getDepartmentsDto())
                .andReturn(Arrays.asList(departmentDto1, departmentDto2));

        replay(departmentService);

        mockMvc.perform(
                get("/departments")
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(
                        MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].departmentId", Matchers.is(1)))
                .andExpect(jsonPath("$[0].departmentName", Matchers.is("name1")))
                .andExpect(jsonPath("$[1].departmentId", Matchers.is(2)))
                .andExpect(jsonPath("$[1].departmentName", Matchers.is("name2")));
    }

    @Test
    public void getDepartmentById() throws Exception {

        Department department = new Department(D_NAME, D_DESC);
        department.setDepartmentId(D_ID);

        expect(departmentService.getDepartmentById(D_ID))
                .andReturn(department);

        replay(departmentService);

        mockMvc.perform(
                get("/departments/{id}", D_ID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isFound())
                .andExpect(content().contentType(
                        MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string("{\"departmentId\":1,"
                        + "\"departmentName\":\"Test dept\","
                        + "\"description\":\"Test desc\"}"));
    }


}
