package com.mindex.challenge.data;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Adam Mick
 */
public class ReportStructure {

    private static final Logger LOG = LoggerFactory.getLogger(ReportStructure.class);

    /**
     * Included the MultiValuedMap from the apache commons collections library
     * added to import for readability and ease of use. This Map collection can
     * be replaced with a simple ArrayList.
     * 
     */
    MultiValuedMap<Employee, Employee> reportingMap = new ArrayListValuedHashMap();

    /**
     * This is the employee the report was created for
     */
    private Employee employee;
   
    /**
     *
     * @param emp
     */
    public ReportStructure(Employee emp) {
        this.employee = emp;
    }
    
    public int getNumberOfReports() {
        return this.reportingMap.size();
    }

    public void addEmployee(Employee employee, Employee reportee) {
        this.reportingMap.put(employee, reportee );
    }
    
    public MultiValuedMap<Employee, Employee> getReportMap(){
        return this.reportingMap;
    }

}
