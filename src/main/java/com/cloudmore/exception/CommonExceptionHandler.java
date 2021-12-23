package com.cloudmore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * CommonExceptionHandler
 *
 * @author perun
 */
@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    public static final Logger LOG = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnyException(Exception ex, ServletWebRequest request) {
        LOG.error("An error occurred during processing request {} at {}, {}",
                request.getRequest().getMethod(), request.getRequest().getRequestURL(), ex);
        return handleExceptionInternal(
                ex,
                new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "other"),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }
}
