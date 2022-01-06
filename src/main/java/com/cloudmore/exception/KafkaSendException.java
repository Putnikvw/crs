package com.cloudmore.exception;

/**
 * ParseEventTimeException
 *
 * @author zen
 */
public class KafkaSendException extends ClientServiceException {

    public KafkaSendException(String message) {
        super(message, ClientProblemCode.KAFKA_SEND_EXCEPTION);
    }
}
