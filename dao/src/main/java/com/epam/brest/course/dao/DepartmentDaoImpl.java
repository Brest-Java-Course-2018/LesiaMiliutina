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

/**
 * Implementation of DepartmentDao interface.
 */
public class DepartmentDaoImpl implements DepartmentDao {

  /**
   * Simplifies the use of JDBC.
   */
  private JdbcTemplate jdbcTemplate;

  /**
   * Allowing using names of parameters rather than '?' placeholders.
   */
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


  /**
   * Constructor for class.
   * @param dataSource dataSource object.
   */
  public DepartmentDaoImpl(final DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.namedParameterJdbcTemplate =
            new NamedParameterJdbcTemplate(dataSource);
  }


  /**
   * Method for getting all rows of table.
   * @return all departments.
   */
  @Override
  public final List<Department> getDepartments() {
    List<Department> departments = jdbcTemplate.query(
            Queries.GET_DEPARTMENTS_SQL, new DepartmentRowMapper());
    return departments;
  }


  /**
   * Method for getting rows from table.
   * @param departmentId department id.
   * @return department by its id value.
   */
  @Override
  public final Department getDepartmentById(final Integer departmentId) {
    SqlParameterSource namedParameters =
            new MapSqlParameterSource("departmentId", departmentId);
    Department department = namedParameterJdbcTemplate.
            queryForObject(Queries.GET_DEPARTMENT_BY_ID_SQL,
                    namedParameters, new DepartmentRowMapper());
    return department;
  }

  /**
   * Method for adding rows in table.
   * @param department added department object.
   * @return added department.
   */
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

  /**
   * Method for updating.
   * @param department department object that needed to be update.
   */
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

  /**
   * Method for deleting rows from table.
   * @param id department id.
   */
  @Override
  public final void deleteDepartmentById(final Integer id) {
    SqlParameterSource namedParameters =
            new MapSqlParameterSource("departmentId", id);
    namedParameterJdbcTemplate.update(
            Queries.DELETE_DEPARTMENT_SQL, namedParameters);
  }

  /**
   * Row mapper for department class.
   */
  private class DepartmentRowMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(final ResultSet resultSet,
                              final int i)
            throws SQLException {
      int k = 1;
      Department department = new Department();

      department.setDepartmentId(resultSet.getInt(k));
      department.setDepartmentName(resultSet.getString(++k));
      department.setDescription(resultSet.getString(++k));
      return department;
    }
  }

  /**
   * SQL queries.
   */
  private class Queries {

    /**
     * SELECT query.
     */
    private static final String GET_DEPARTMENTS_SQL =
            "SELECT departmentId, departmentName, description FROM department";

    /**
     * SELECT WHERE query.
     */
    private static final String GET_DEPARTMENT_BY_ID_SQL =
            "SELECT departmentId, departmentName, description "
                    + "FROM department WHERE departmentId = :departmentId";

    /**
     * INSERT INTO query.
     */
    private static final String ADD_DEPARTMENT_SQL =
            "INSERT INTO department (departmentName, description) "
                    + "VALUES (:departmentName, :description)";

    /**
     * UPDATE query.
     */
    private static final String UPDATE_DEPARTMENT_SQL =
            "UPDATE department SET departmentName = :departmentName, "
                    + "description = :description "
                    + "WHERE departmentId = :departmentId";

    /**
     * DELETE query.
     */
    private static final String DELETE_DEPARTMENT_SQL =
            "DELETE FROM department WHERE departmentId = :departmentId";
  }

}
