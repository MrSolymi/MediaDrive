package me.solymi.exception;

import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ApiException extends ResponseStatusException {
    private final HttpHeaders headers;
    private final Object[] messageDetailArguments;

    public ApiException(HttpStatus status){
        super(status);
        this.headers = HttpHeaders.EMPTY;
        this.messageDetailArguments = new String[0];
    }

    public ApiException(HttpStatus status, String reason){
        super(status, reason);
        this.headers = HttpHeaders.EMPTY;
        this.messageDetailArguments = new String[0];
    }

    public ApiException(HttpStatus status, String reason, Object... args) {
        super(status, reason);
        this.headers = HttpHeaders.EMPTY;
        this.messageDetailArguments = args;
    }

    public static ApiException badRequest(String reason) {
        return new ApiException(HttpStatus.BAD_REQUEST, reason);
    }

    public static ApiException forbidden(final String reason) {
        return new ApiException(HttpStatus.FORBIDDEN, reason);
    }

    public static ApiException unauthorized(final String reason) {
        return new ApiException(HttpStatus.UNAUTHORIZED, reason);
    }
}
