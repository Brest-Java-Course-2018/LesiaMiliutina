package com.epam.brest.course.dao;

import com.epam.brest.course.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

/**
 * Implementation of EmployeeDao interface.
 */
public class EmployeeDaoImpl implements EmployeeDao {


    /**
     * Select sql query.
     */
    @Value("${employee.select}")
    private String employeeSelect;
    /**
     * Select where sql query.
     */
    @Value("${employee.selectById}")
    private String employeeSelectById;
    /**
     * Insert sql query.
     */
    @Value("${employee.add}")
    private String employeeAdd;
    /**
     * Update sql query.
     */
    @Value("${employee.update}")
    private String employeeUpdate;
    /**
     * Delete sql query.
     */
    @Value("${employee.delete}")
    private String employeeDelete;

    /**
     * Constant variable.
     */
    public static final String EMPLOYEE_ID = "employeeId";

    /**
     * Allowing using names of parameters rather than '?' placeholders.
     */
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    /**
     * Setter for field namedParameterJdbcTemplate.
     * @param namedParameter for named queries.
     */
    public final void setNamedParameterJdbcTemplate(
             final NamedParameterJdbcTemplate namedParameter) {
        this.namedParameterJdbcTemplate = namedParameter;
    }


    /**
     * Method for getting all rows of table.
     * @return all employees.
     */
    @Override
    public  final List<Employee> getEmployees() {
        List<Employee> employees =
                namedParameterJdbcTemplate.getJdbcOperations().
                        query(employeeSelect, BeanPropertyRowMapper.
                                newInstance(Employee.class));
        return employees;
    }

    /**
     * Method for getting rows from table.
     * @param employeeId employee's id.
     * @return employee by id value.
     */
    @Override
    public final Employee getEmployeeById(final Integer employeeId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(EMPLOYEE_ID, employeeId);
        Employee employee = namedParameterJdbcTemplate.
                queryForObject(employeeSelectById, namedParameters,
                        BeanPropertyRowMapper.newInstance(Employee.class));
        return employee;
    }

    /**
     * Method for adding rows in table.
     * @param employee added employee object.
     * @return added employee.
     */
    @Override
    public final Employee addEmployee(final Employee employee) {
        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(employee);
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.
                update(employeeAdd, namedParameters, generatedKeyHolder);
        employee.setEmployeeId(generatedKeyHolder.getKey().intValue());
        return employee;
    }

    /**
     * Method for updating.
     * @param employee employee object that needed to be update.
     */
    @Override
    public final void updateEmployee(final Employee employee) {
        SqlParameterSource namedParameters =
                new BeanPropertySqlParameterSource(employee);
        namedParameterJdbcTemplate.update(employeeUpdate, namedParameters);

    }

    /**
     * Method for deleting rows from table.
     * @param employeeId employee's id.
     */
    @Override
    public final void deleteEmployeeById(final Integer employeeId) {
        namedParameterJdbcTemplate.getJdbcOperations().update(
                employeeDelete, employeeId);
    }
}
