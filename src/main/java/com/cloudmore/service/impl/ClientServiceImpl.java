package com.cloudmore.service.impl;

import com.cloudmore.domain.Client;
import com.cloudmore.dto.ClientDto;
import com.cloudmore.mapper.ClientMapper;
import com.cloudmore.message.model.KafkaClientMessage;
import com.cloudmore.message.producer.ClientRegistrationServiceProducer;
import com.cloudmore.repository.ClientServiceRepository;
import com.cloudmore.service.ClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
    private long tax;

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
        BigDecimal wageWithTax = addTaxes(client.getWage());
        client.setWage(wageWithTax);
        repository.save(client);

        return mapper.toDto(client);

    }

    private BigDecimal addTaxes(BigDecimal value) {
        return value.add(value.multiply(new BigDecimal(tax).divide(new BigDecimal(100), 4, RoundingMode.HALF_EVEN)));
    }
}
