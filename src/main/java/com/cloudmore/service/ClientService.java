package com.cloudmore.service;

import com.cloudmore.dto.ClientDto;
import com.cloudmore.message.model.KafkaClientMessage;
import org.springframework.stereotype.Service;

/**
 * ClientService
 *
 * @author perun
 */
public interface ClientService {

    ClientDto saveClient(KafkaClientMessage message);

}
