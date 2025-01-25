package com.mindex.challenge.data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Adam Mick
 */
abstract class Compensation {

    LocalDate effectiveDate = LocalDate.now();
    BigDecimal salary = new BigDecimal("00.00");

    public Compensation(LocalDate effectiveDate, BigDecimal salary) {
        this.effectiveDate = effectiveDate;
        this.salary = salary;
    }

    public Compensation() {
    }

}
