package com.cloudmore.mapper;

import com.cloudmore.domain.Client;
import com.cloudmore.dto.ClientDto;
import com.cloudmore.message.model.KafkaClientMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

/**
 * ClientMapper
 *
 * @author perun
 */
@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "eventTime", expression = "java(convertToEpoch(dto.getEventTime()))" )
    Client toDomain(ClientDto dto);

    ClientDto toDto(Client client);

    KafkaClientMessage toKafkaMessage(ClientDto dto);

    default Long convertToEpoch(String source) {
        return Instant.parse(source).getEpochSecond();
    }

}
