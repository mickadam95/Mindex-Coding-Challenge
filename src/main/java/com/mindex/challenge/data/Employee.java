package com.mindex.challenge.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.annotation.Id;

public class Employee extends Compensation {
    @Id //added the Id identifier 
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private List<Employee> directReports;

    public Employee() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     *
     * @return List Employee
     */
    public List<Employee> getDirectReports() {
        return directReports;
    }

    /**
     *
     * @param directReports
     */
    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }

    /**
     *
     * @param effectiveDate
     */
    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    /**
     *
     * @param salary
     */
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    /**
     *
     * @return LocalDate
     */
    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    /**
     *
     * @return BigDecimal
     */
    public BigDecimal getSalary() {
        return salary;
    }
}
