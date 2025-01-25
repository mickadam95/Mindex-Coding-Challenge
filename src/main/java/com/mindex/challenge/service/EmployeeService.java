package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import java.math.BigDecimal;
import java.time.LocalDate;
public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);
    BigDecimal getEmployeeComp(String id);
    Employee updateCompenstaion(String id, BigDecimal compensation, LocalDate date);
    
}
