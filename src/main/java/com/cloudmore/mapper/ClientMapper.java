package com.cloudmore.mapper;

import com.cloudmore.domain.Client;
import com.cloudmore.dto.ClientDto;
import com.cloudmore.message.model.KafkaClientMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

/**
 * ClientMapper
 *
 * @author perun
 */
@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "eventTime", expression = "java(convertToEpoch(dto.getEventTime()))")
    Client toDomain(ClientDto dto);

    @Mappings({
            @Mapping(target = "wage", expression = "java(roundWageValue(client.getWage()))"),
            @Mapping(target = "eventTime", expression = "java(epochToString(client.getEventTime()))")
    })
    ClientDto toDto(Client client);

    KafkaClientMessage toKafkaMessage(ClientDto dto);

    default Long convertToEpoch(String source) {
        return Instant.parse(source).toEpochMilli();
    }

    default String epochToString(Long epochTime) {
        return Instant.ofEpochMilli(epochTime).toString();
    }

    default BigDecimal roundWageValue(BigDecimal wage) {
        return wage.setScale(2, RoundingMode.HALF_EVEN);
    }

}
