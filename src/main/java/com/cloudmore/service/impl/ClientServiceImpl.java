package com.cloudmore.service.impl;

import com.cloudmore.domain.Client;
import com.cloudmore.dto.ClientDto;
import com.cloudmore.mapper.ClientMapper;
import com.cloudmore.message.model.KafkaClientMessage;
import com.cloudmore.message.producer.ClientRegistrationServiceProducer;
import com.cloudmore.repository.ClientServiceRepository;
import com.cloudmore.service.ClientService;
import com.cloudmore.utils.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.cloudmore.utils.Util.*;

/**
 * ClientServiceImpl
 *
 * @author perun
 */
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientMapper mapper;
    private final ClientServiceRepository repository;
    private final ClientRegistrationServiceProducer clientProducer;

    @Value("${client-service.tax}")
    private static long tax;

    public ClientServiceImpl(ClientMapper mapper, ClientServiceRepository repository, ClientRegistrationServiceProducer clientProducer) {
        this.mapper = mapper;
        this.repository = repository;
        this.clientProducer = clientProducer;
    }

    @Override
    @Transactional
    public ClientDto saveClient(ClientDto body) {
        KafkaClientMessage message = mapper.toKafkaMessage(body);
        clientProducer.sendClientMessage(message);

        Client client = mapper.toDomain(body);
        client.setWage(addTaxes(client.getWage(), tax));
        repository.save(client);

        return mapper.toDto(client);

    }
}
