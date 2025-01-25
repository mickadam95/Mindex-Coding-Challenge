package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportStructure;
import com.mindex.challenge.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adam
 */
@Service
public class ReportServiceImpl implements ReportService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     *
     * @param id This is the Employee Id to start the report calculation
     * @return Returns the total number of direct reports on the passed Employee
     */
    @Override
    public ReportStructure getReportStructure(String id) {
        Employee employee = employeeRepository.findByEmployeeId(id);
        ReportStructure report = new ReportStructure(employee);
        calculateReport(report, employee);

        return report;
    }

    /**
     * This method will start with the base level employee and will recursively go down the list of direct reports
     * and add them to a ReportStructre map.
     * @param report The report the information (Report structure map) is saved in
     * @param employee The main employee the report is run on. This will be the top level of the map.
     */
    private void calculateReport(ReportStructure report, Employee employee) {
        LOG.debug("Calculating the direct reports for id [{}]", employee.getEmployeeId());
        if (employee.getDirectReports() != null) {
            for (Employee person : employee.getDirectReports()) {
                
                Employee reportee = employeeRepository.findByEmployeeId(person.getEmployeeId());
                
                LOG.debug("Adding employee [{}] over reportee [{}]", employee.getFirstName(), reportee.getFirstName());
                report.addEmployee(employee, reportee);
                calculateReport(report, reportee);
            }
        }
    }
}
