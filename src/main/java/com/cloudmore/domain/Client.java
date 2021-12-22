package com.cloudmore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Client
 *
 * @author perun
 */
@Entity
public class Client extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private BigDecimal wage;

    @Column(nullable = false, name = "event_time")
    private Long eventTime;

    public Client() {
    }

    public Client(Long id, LocalDateTime createAt, String createBy, LocalDateTime modifiedAt, String modifiedBy, String name, String surname, BigDecimal wage, Long eventTime) {
        super(id, createAt, createBy, modifiedAt, modifiedBy);
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

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(surname, client.surname) && Objects.equals(eventTime, client.eventTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, eventTime);
    }
}
