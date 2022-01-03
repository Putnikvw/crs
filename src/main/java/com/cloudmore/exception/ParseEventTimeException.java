package com.cloudmore.exception;

/**
 * ParseEventTimeException
 *
 * @author zen
 */
public class ParseEventTimeException extends ClientServiceException {

    public ParseEventTimeException(String message) {
        super(message, ClientProblemCode.CANNOT_PARSE_EVENTTIME);
    }
}
