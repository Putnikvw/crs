package com.cloudmore.mapper;

import com.cloudmore.builder.ClientBuilder;
import com.cloudmore.domain.Client;
import com.cloudmore.dto.ClientDto;
import com.cloudmore.exception.ParseEventTimeException;
import com.cloudmore.message.model.KafkaClientMessage;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.cloudmore.builder.ClientBuilder.*;

/**
 * ClientMapperTest
 *
 * @author zen
 */
public class ClientMapperTest implements WithAssertions {

    private ClientMapper mapper = Mappers.getMapper(ClientMapper.class);

    @Test
    public void toDomainTest() {
        ClientBuilder clientBuilder = aClient().getFullClient();
        Client result = mapper.toDomain(clientBuilder.getClientDto());
        Client expected = clientBuilder.getClientModel();

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void toDomainTest_ThrowParseEventTimeException() {
        ClientBuilder clientBuilder = aClient()
                .withName(DEFAULT_NAME)
                .withSurname(DEFAULT_SURNAME)
                .withWage(new BigDecimal("23.4"))
                .withEventTime(LocalDateTime.now().toString());

        assertThatExceptionOfType(ParseEventTimeException.class)
                .isThrownBy(() -> mapper.toDomain(clientBuilder.getClientDto()))
                .withMessage("Cannot parse eventTime");
    }

    @Test
    public void toDtoTest() {
        ClientBuilder clientBuilder = aClient()
                .withName("Alex")
                .withSurname(DEFAULT_SURNAME)
                .withWage(new BigDecimal("6547.87"))
                .withEventTime(DEFAULT_EVENT_TIME);
        ClientDto result = mapper.toDto(clientBuilder.getClientModel());
        ClientDto expected = clientBuilder.getClientDto();

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void toKafkaMessageTest() {
        ClientBuilder clientBuilder = aClient()
                .withName("Marek")
                .withSurname("Simpson")
                .withWage(new BigDecimal("6547.87"))
                .withEventTime(DEFAULT_EVENT_TIME);
        KafkaClientMessage result = mapper.toKafkaMessage(clientBuilder.getClientDto());
        KafkaClientMessage expected = clientBuilder.getKafkaMessageClient();

        assertThat(result).usingRecursiveComparison().isEqualTo(expected);
    }
}
