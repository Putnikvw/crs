package com.cloudmore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * ClientDto
 *
 * @author perun
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("wage")
    private BigDecimal wage;

    @JsonProperty("eventTime")
    private String eventTime;

    public ClientDto(@JsonProperty("name") String name,
                     @JsonProperty("surname")String surname,
                     @JsonProperty("wage")BigDecimal wage,
                     @JsonProperty("eventTime")String eventTime) {

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
