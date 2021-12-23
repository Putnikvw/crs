package com.cloudmore.message.producer;

import com.cloudmore.message.model.KafkaClientMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * ClientRegistrationServiceProducer
 *
 * @author perun
 */
@Component
public class ClientRegistrationServiceProducer {

    private static final Logger LOG = LoggerFactory.getLogger(ClientRegistrationServiceProducer.class);

    @Value("${kafka.client-service.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, KafkaClientMessage> kafkaTemplate;

    public void sendClientMessage(KafkaClientMessage message) {
        LOG.info("Produce message -> {}", message);
        kafkaTemplate.send(topic, message);
    }
}
