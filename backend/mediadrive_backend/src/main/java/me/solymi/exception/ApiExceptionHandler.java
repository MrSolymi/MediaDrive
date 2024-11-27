package me.solymi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "me.solymi")
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiException> handleException(ApiException e) {
        return new ResponseEntity<>(e, e.getHeaders(), e.getStatusCode().value());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiException> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(ApiException.forbidden(e.getMessage()), HttpStatus.FORBIDDEN);
    }
}
