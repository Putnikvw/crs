package com.cloudmore.utils;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Util
 *
 * @author zen
 */
public class Util {

    public static BigDecimal addTaxes(BigDecimal value, long tax) {
        return value.add(value.multiply(new BigDecimal(tax).divide(new BigDecimal(100), 4, RoundingMode.HALF_EVEN)));
    }
}
