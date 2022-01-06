package com.cloudmore.exception;

import org.springframework.http.HttpStatus;

/**
 * ErrorResponse
 *
 * @author perun
 */
public class ClientServiceException extends RuntimeException {

    private final ClientProblemCode clientType;
    private final HttpStatus httpStatus;

    public ClientServiceException(String message, ClientProblemCode clientProblemCode) {
        super(message);
        this.clientType = clientProblemCode;
        this.httpStatus = HttpStatus.valueOf(clientProblemCode.getHttpStatusCode());
    }

    public ClientProblemCode getClientType() {
        return clientType;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
