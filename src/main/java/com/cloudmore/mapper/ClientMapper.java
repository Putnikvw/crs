package com.cloudmore.mapper;

import com.cloudmore.domain.Client;
import com.cloudmore.dto.ClientDto;
import com.cloudmore.exception.ClientServiceException;
import com.cloudmore.exception.ParseEventTimeException;
import com.cloudmore.message.model.KafkaClientMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

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
        try {
            return Instant.parse(source).toEpochMilli();
        } catch (Exception ex) {
            throw new ParseEventTimeException("Cannot parse eventTime");
        }
    }

    default String epochToString(Long epochTime) {
        return Instant.ofEpochMilli(epochTime).toString();
    }

    default BigDecimal roundWageValue(BigDecimal wage) {
        return wage.setScale(2, RoundingMode.HALF_EVEN);
    }

}
