package com.cloudmore.service.impl;

import com.cloudmore.dto.ClientDto;
import com.cloudmore.message.model.KafkaClientMessage;
import com.cloudmore.service.ClientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * ClientServiceImpl
 *
 * @author perun
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Override
    @Transactional
    public ClientDto saveClient(KafkaClientMessage message) {
        return null;
    }
}
