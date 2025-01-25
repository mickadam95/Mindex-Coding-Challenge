package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    /**
     * This can be used to update Employee compensation.
     * @param employee
     * @return
     */
    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
    
    /**
     * Method that returns the salary associated with the Employee id.
     * @param id
     * @return
     */
    @Override
    public BigDecimal getEmployeeComp(String id){
        LOG.debug("Getting employee compensation for id [{}]", id);
        
        Employee employee = employeeRepository.findByEmployeeId(id);
        return employee.getSalary();
    }
    
    /**
     *
     * @param id
     * @param compensation
     * @param date
     * @return
     */
    @Override
    public Employee updateCompenstaion(String id, BigDecimal compensation, LocalDate date){
        LOG.debug("Updating compensation for employee id [{}]", id);
        
        Employee employee = employeeRepository.findByEmployeeId(id);
        
        employee.setSalary(compensation);
        employee.setEffectiveDate(date);
        
        return update(employee);
    }
}
