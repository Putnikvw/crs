package com.cloudmore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;

/**
 * ClientDto
 *
 * @author perun
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDto {
    private String name;
    private String surname;
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
