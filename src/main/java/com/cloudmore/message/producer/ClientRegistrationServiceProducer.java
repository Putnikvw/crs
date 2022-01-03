package com.cloudmore.message.producer;

import com.cloudmore.exception.KafkaSendException;
import com.cloudmore.message.model.KafkaClientMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

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

        ListenableFuture<SendResult<String, KafkaClientMessage>> future = kafkaTemplate.send(topic, message);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, KafkaClientMessage> result) {
                LOG.info("Produce message -> {}", message);
            }

            @Override
            public void onFailure(Throwable ex) {
                LOG.error(ex.getMessage());
                throw new KafkaSendException("Error to send data");
            }
        });
    }
}
