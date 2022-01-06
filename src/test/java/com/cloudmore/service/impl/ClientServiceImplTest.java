package com.cloudmore.service.impl;

import com.cloudmore.builder.ClientBuilder;
import com.cloudmore.dto.ClientDto;
import com.cloudmore.mapper.ClientMapper;
import com.cloudmore.message.model.KafkaClientMessage;
import com.cloudmore.message.producer.ClientRegistrationServiceProducer;
import com.cloudmore.repository.ClientServiceRepository;
import com.cloudmore.utils.Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * ClientServiceImplTest
 *
 * @author zen
 */
@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientServiceRepository repository;

    @Mock
    private ClientMapper mapper;

    @Mock
    ClientRegistrationServiceProducer kafkaService;

    @InjectMocks
    private ClientServiceImpl service;

    private ClientBuilder clientBuilder = ClientBuilder.aClient().getFullClient();
    private final long taxValue = 10L;

    @Test
    void saveClientTest() {
        KafkaClientMessage message = clientBuilder.getKafkaMessageClient();
        when(mapper.toKafkaMessage(any())).thenReturn(message);
        doNothing().when(kafkaService).sendClientMessage(message);
        doReturn(clientBuilder.getClientModel()).when(mapper).toDomain(any());

        BigDecimal wageWithTax = Util.addTaxes(clientBuilder.getClientModel().getWage(), taxValue);
        ClientBuilder resultClientBuilder = clientBuilder.withWage(wageWithTax);

        doReturn(resultClientBuilder.getClientModel()).when(repository).save(any());
        doReturn(resultClientBuilder.getClientDto()).when(mapper).toDto(any());

        ClientDto result = service.saveClient(clientBuilder.getClientDto());

        assertThat(result).usingRecursiveComparison().isEqualTo(resultClientBuilder.getClientDto());

        verify(mapper).toKafkaMessage(any());
        verify(kafkaService).sendClientMessage(message);
        verify(mapper).toDomain(any());
        verify(repository).save(any());
        verify(mapper).toDto(any());
    }
}