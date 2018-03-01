package com.epam.brest.course.dao;

import com.epam.brest.course.model.Department;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

  private JdbcTemplate jdbcTemplate;

  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


  public DepartmentDaoImpl(final DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.namedParameterJdbcTemplate =
            new NamedParameterJdbcTemplate(dataSource);
  }


  @Override
  public final List<Department> getDepartments() {
    List<Department> departments = jdbcTemplate.query(
            Queries.GET_DEPARTMENTS_SQL, new DepartmentRowMapper());
    return departments;
  }


  @Override
  public final Department getDepartmentById(final Integer departmentId) {
    SqlParameterSource namedParameters =
            new MapSqlParameterSource("departmentId", departmentId);
    Department department = namedParameterJdbcTemplate.
            queryForObject(Queries.GET_DEPARTMENT_BY_ID_SQL,
                    namedParameters, new DepartmentRowMapper());
    return department;
  }

  @Override
  public final Department addDepartment(final Department department) {
    SqlParameterSource namedParameters =
            new MapSqlParameterSource(
                    "departmentName", department.getDepartmentName())
                    .addValue("description", department.getDescription());
    namedParameterJdbcTemplate.update(
            Queries.ADD_DEPARTMENT_SQL, namedParameters);
    return department;
  }

  @Override
  public final void updateDepartment(final Department department) {
    SqlParameterSource namedParameters =
            new MapSqlParameterSource(
                    "departmentId", department.getDepartmentId())
                    .addValue("departmentName", department.getDepartmentName())
                    .addValue("description", department.getDescription());
    namedParameterJdbcTemplate.update(
            Queries.UPDATE_DEPARTMENT_SQL, namedParameters);
  }

  @Override
  public final void deleteDepartmentById(final Integer id) {
    SqlParameterSource namedParameters =
            new MapSqlParameterSource("departmentId", id);
    namedParameterJdbcTemplate.update(
            Queries.DELETE_DEPARTMENT_SQL, namedParameters);
  }

  private class DepartmentRowMapper implements RowMapper<Department> {

    @Override
    public Department mapRow (final ResultSet resultSet,
                              final int i)
            throws SQLException {
      Department department = new Department();
      department.setDepartmentId(resultSet.getInt(1));
      department.setDepartmentName(resultSet.getString(2));
      department.setDescription(resultSet.getString(3));
      return department;
    }
  }

  private class Queries {
    private static final String GET_DEPARTMENTS_SQL =
            "SELECT departmentId, departmentName, description FROM department";

    private static final String GET_DEPARTMENT_BY_ID_SQL =
            "SELECT departmentId, departmentName, description "
                    + "FROM department WHERE departmentId = :departmentId";

    private static final String ADD_DEPARTMENT_SQL =
            "INSERT INTO department (departmentName, description) "
                    + "VALUES (:departmentName, :description)";

    private static final String UPDATE_DEPARTMENT_SQL =
            "UPDATE department SET departmentName = :departmentName, "
                    + "description = :description "
                    + "WHERE departmentId = :departmentId";

    private static final String DELETE_DEPARTMENT_SQL =
            "DELETE FROM department WHERE departmentId = :departmentId";


  }

}
