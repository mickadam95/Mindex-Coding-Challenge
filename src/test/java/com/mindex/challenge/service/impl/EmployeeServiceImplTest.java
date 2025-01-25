package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeServiceImplTest {
    
    private String employeeUrl;
    private String employeeIdUrl;
    
    @Autowired
    private EmployeeService employeeService;
    
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
    ReportService reportService;
    
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImplTest.class);
    
    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
    }
    
    @Test
    public void testCreateReadUpdate() {
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("John");
        testEmployee.setLastName("Doe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Developer");

        // Create checks
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();
        
        assertNotNull(createdEmployee.getEmployeeId());
        assertEmployeeEquivalence(testEmployee, createdEmployee);

        // Read checks
        Employee readEmployee = restTemplate.getForEntity(employeeIdUrl, Employee.class, createdEmployee.getEmployeeId()).getBody();
        assertEquals(createdEmployee.getEmployeeId(), readEmployee.getEmployeeId());
        assertEmployeeEquivalence(createdEmployee, readEmployee);

        // Update checks
        readEmployee.setPosition("Development Manager");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        Employee updatedEmployee
                = restTemplate.exchange(employeeIdUrl,
                        HttpMethod.PUT,
                        new HttpEntity<Employee>(readEmployee, headers),
                        Employee.class,
                        readEmployee.getEmployeeId()).getBody();
        
        assertEmployeeEquivalence(readEmployee, updatedEmployee);
    }
    
    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }

    /**
     * Test for the get salary and update salary methods
     */
    @Test
    public void testSalaryGetUpdate() {
        
        BigDecimal dec = new BigDecimal("100000.00");

        //Get one of the existing employees that have no compensation
        Employee testEmp = employeeService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        //compensation should initially be zero because it was never set.
        assertEquals(testEmp.getSalary(), BigDecimal.ZERO.setScale(2));
        
        employeeService.updateCompenstaion("16a596ae-edd3-4847-99fe-c4518e82c86f", dec, LocalDate.now());
        
        testEmp = employeeService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        //check the value on the employee was updates
        assertEquals(testEmp.getSalary(), dec);
    }
    
    @Test
    public void testEmployeeReport() {
        
        ReportStructure report = reportService.getReportStructure("16a596ae-edd3-4847-99fe-c4518e82c86f");
        //The head of the reporting tree should have 4 direct reports
        assertEquals(report.getNumberOfReports(), 4);
        assertNotNull(report.getReportMap());
        
        /**
         * Uncomment to visualize the map during testing.
         *
        for (Map.Entry<Employee, Employee> entry : report.getReportMap().entries()) {
            LOG.debug("Key: [{}]", entry.getKey().getFirstName());
            LOG.debug("Value: [{}]", entry.getValue().getFirstName());
        }*/
    }
    
}
