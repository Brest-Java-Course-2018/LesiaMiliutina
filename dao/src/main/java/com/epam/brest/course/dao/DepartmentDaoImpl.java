package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of DepartmentDao interface.
 */
public class DepartmentDaoImpl implements DepartmentDao {

  public static final String DEPARTMENT_ID = "departmentId";
  public static final String DEPARTMENT_NAME = "departmentName";
  public static final String DESCRIPTION = "description";

  @Value("${department.select}")
  private String departmentSelect;
  @Value("${department.selectById}")
  private String departmentSelectById;
  @Value("${department.add}")
  private String departmentAdd;
  @Value("${department.update}")
  private String departmentUpdate;
  @Value("${department.delete}")
  private String departmentDelete;
  @Value("${department.check}")
  private String departmentCheck;



  public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
    this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
  }

  /**
   * Allowing using names of parameters rather than '?' placeholders.
   */
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  /**
   * Method for getting all rows of table.
   * @return all departments.
   */
  @Override
  public final List<Department> getDepartments() {
    List<Department> departments = namedParameterJdbcTemplate.getJdbcOperations().query(
            departmentSelect, new DepartmentRowMapper());
    return departments;
  }


  /**
   * Method for getting rows from table.
   * @param departmentId department id.
   * @return department by its id value.
   */
//  @Override
//  public final Department getDepartmentById(final Integer departmentId) {
//    SqlParameterSource namedParameters =
//            new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
//    Department department = namedParameterJdbcTemplate.
//            queryForObject(departmentSelectById,
//                    namedParameters, new DepartmentRowMapper());
//    return department;
//  }

    /**
     * Method for getting rows from table.
     * @param departmentId department id.
     * @return department by its id value.
     */
    @Override
    public final Department getDepartmentById(final Integer departmentId) {
        SqlParameterSource namedParameters =
                new MapSqlParameterSource(DEPARTMENT_ID, departmentId);
        Department department = namedParameterJdbcTemplate
                .queryForObject(departmentSelectById, namedParameters,
                        BeanPropertyRowMapper.newInstance(Department.class));
        return department;
    }

    /**
   * Method for adding rows in table.
   * @param department added department object.
   * @return added department.
   */
  @Override
  public final Department addDepartment(final Department department) {
    MapSqlParameterSource namedParameters =
            new MapSqlParameterSource(
                    DEPARTMENT_NAME, department.getDepartmentName());
    Integer result = namedParameterJdbcTemplate.queryForObject(
            departmentCheck, namedParameters, Integer.class);
    if(result == 0){
      namedParameters = new MapSqlParameterSource();
      namedParameters.addValue(DEPARTMENT_NAME, department.getDepartmentName());
      namedParameters.addValue(DESCRIPTION, department.getDescription());

      KeyHolder generateKeyHolder = new GeneratedKeyHolder();
      namedParameterJdbcTemplate.update(departmentAdd, namedParameters, generateKeyHolder);
      department.setDepartmentId(generateKeyHolder.getKey().intValue());
    } else {
      throw new IllegalArgumentException("Department with the same name is already exists.");
    }
    return department;
  }

  /**
   * Method for updating.
   * @param department department object that needed to be update.
   */
  @Override
  public final void updateDepartment(final Department department) {
    SqlParameterSource namedParameters =
            new BeanPropertySqlParameterSource(department);
    namedParameterJdbcTemplate.update(departmentUpdate, namedParameters);
  }

  /**
   * Method for deleting rows from table.
   * @param departmentId department id.
   */
  @Override
  public final void deleteDepartmentById(final Integer departmentId) {
    namedParameterJdbcTemplate.getJdbcOperations().update(
            departmentDelete, departmentId);
  }

  /**
   * TODO: delete this class later
   * Row mapper for department class.
   */
  private class DepartmentRowMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(final ResultSet resultSet,
                              final int i)
            throws SQLException {

      Department department = new Department();

      department.setDepartmentId(resultSet.getInt(DEPARTMENT_ID));
      department.setDepartmentName(resultSet.getString(DEPARTMENT_NAME));
      department.setDescription(resultSet.getString(DESCRIPTION));
      return department;
    }
  }

}
