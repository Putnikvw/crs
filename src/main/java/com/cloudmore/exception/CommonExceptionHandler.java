package com.cloudmore.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CommonExceptionHandler
 *
 * @author perun
 */
@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    public static final Logger LOG = LoggerFactory.getLogger(CommonExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        logErrors(request, ex);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }

    @ExceptionHandler(ClientServiceException.class)
    public ResponseEntity<CustomException> handleClientServiceException (ClientServiceException ex, WebRequest request) {
        LOG.error("An error occurred during processing request", ex);
        CustomException errors = new CustomException();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getClientType().name());
        errors.setStatus(ex.getHttpStatus().value());

        return new ResponseEntity<>(errors, ex.getHttpStatus());
    }

    private void logErrors(WebRequest request, Exception ex) {
        ServletWebRequest webRequest = (ServletWebRequest) request;
        LOG.error("Wrong input parameters when calling {} at {}", webRequest.getRequest().getMethod(),
                webRequest.getRequest().getRequestURL(), ex);
    }
}
