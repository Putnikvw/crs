package com.cloudmore.builder;

import com.cloudmore.domain.Client;
import com.cloudmore.dto.ClientDto;
import com.cloudmore.message.model.KafkaClientMessage;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * ClientMother
 *
 * @author zen
 */
public class ClientBuilder {

    public static final String DEFAULT_NAME = "James";
    public static final String DEFAULT_SURNAME = "Bond";
    public static final BigDecimal DEFAULT_WAGE = new BigDecimal("124.65");
    public static final String DEFAULT_EVENT_TIME = Instant.ofEpochMilli(LocalDateTime.now().getNano()).toString();

    private String name;
    private String surname;
    private BigDecimal wage;
    private String eventTime;

    private ClientBuilder() {
    }

    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }

    public ClientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public ClientBuilder withWage(BigDecimal wage) {
        this.wage = wage;
        return this;
    }

    public ClientBuilder withEventTime(String eventTime) {
        this.eventTime = eventTime;
        return this;
    }

    public ClientBuilder getFullClient() {
        return ClientBuilder
                .aClient()
                .withName(DEFAULT_NAME)
                .withSurname(DEFAULT_SURNAME)
                .withWage(DEFAULT_WAGE)
                .withEventTime(DEFAULT_EVENT_TIME);
    }

    public ClientDto getClientDto() {
        return new ClientDto(name, surname, wage, eventTime);
    }

    public KafkaClientMessage getKafkaMessageClient() {
        return new KafkaClientMessage(name, surname, wage, eventTime);
    }

    public Client getClientModel() {
        return new Client(name, surname, wage, convertLocalDateTimeToLong(eventTime));
    }

    private Long convertLocalDateTimeToLong(String localDateTime) {
        return Instant.parse(localDateTime).toEpochMilli();
    }
}
