package com.epam.brest.course.dto;

/**
 * DTO for department.
 */
public class DepartmentDto {


    /**
     * Default constructor.
     */
    public DepartmentDto() {
    }

    /**
     * Constructor with parameters.
     * @param dId id of department.
     * @param dName name of department.
     * @param avg average salary.
     */
    public DepartmentDto(final Integer dId,
                         final String dName,
                         final Integer avg) {
        this.departmentId = dId;
        this.departmentName = dName;
        this.avgSalary = avg;
    }

    /**
     * Id of department.
     */
    private Integer departmentId;

    /**
     * Name of department.
     */
    private String departmentName;

    /**
     * Average salary of department.
     */
    private Integer avgSalary;

    /**
     * Getter for id field.
     * @return id.
     */
    public final int getDepartmentId() {
        return departmentId;
    }

    /**
     * Setter for id field.
     * @param dId id of department.
     */
    public final void setDepartmentId(final Integer dId) {
        this.departmentId = dId;
    }

    /**
     * Getter for department name.
     * @return name of department.
     */
    public final String getDepartmentName() {
        return departmentName;
    }

    /**
     * Setter for department name.
     * @param dName name of department.
     */
    public final void setDepartmentName(final String dName) {
        this.departmentName = dName;
    }

    /**
     * Getter for average salary field.
     * @return average salary of department.
     */
    public final Integer getAvgSalary() {
        return avgSalary;
    }

    /**
     * Setter for average salary field.
     * @param avg average salary.
     */
    public final void setAvgSalary(final Integer avg) {
        this.avgSalary = avg;
    }

    /**
     * Representation of DepartmentDto object.
     * @return string representation.
     */
    @Override
    public final String toString() {
        return "DepartmentDto{"
                + "departmentId="
                + departmentId
                + ", departmentName='"
                + departmentName + '\''
                + ", avgSalary=" + avgSalary + '}';
    }
}
