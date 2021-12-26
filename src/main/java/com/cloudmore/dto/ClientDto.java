package com.cloudmore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * ClientDto
 *
 * @author perun
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDto {

    @NotEmpty(message = "Name can't be empty and it should be at least 2 characters")
    @Size(min = 2)
    private String name;

    @NotEmpty(message = "Surname can't be empty and it should be at least 2 characters")
    @Size(min = 2)
    private String surname;

    @DecimalMin(value = "0.0")
    private BigDecimal wage;

    private String eventTime;


    public ClientDto(String name, String surname, BigDecimal wage, String eventTime) {
        this.name = name;
        this.surname = surname;
        this.wage = wage;
        this.eventTime = eventTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }
}
