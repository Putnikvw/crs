package com.cloudmore.message.producer;

import com.cloudmore.message.model.KafkaClientMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * ProducerService
 *
 * @author perun
 */
@Service
public class ClientRegistrationServiceProducer {

    private static final Logger logger = LoggerFactory.getLogger(ClientRegistrationServiceProducer.class);

    @Value("kafka.client-service")
    private static String topic;

    @Autowired
    private KafkaTemplate<String, KafkaClientMessage> kafkaTemplate;

    public void sendClientMessage(KafkaClientMessage message) {
        logger.info("Produce message -> {}", message);
        this.kafkaTemplate.send(topic, message);
    }
}
