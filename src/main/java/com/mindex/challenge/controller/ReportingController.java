package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportStructure;
import com.mindex.challenge.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Adam Mick
 */
@RestController
public class ReportingController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingController.class);

    @Autowired
    private ReportService reportService;

    /**
     * This is an endpoint that creates the ReportStructure containing a map of all direct reports under a specific
     * id and the integer number of those direct reports.
     * 
     * @param id
     * @return The ReportStructure for the passed Employee id
     */
    @GetMapping("/reports/{id}")
    public ReportStructure directReports(@PathVariable String id) {
          
        LOG.debug("Received employee report request for id [{}]", id);

        return reportService.getReportStructure(id);
    }

}
