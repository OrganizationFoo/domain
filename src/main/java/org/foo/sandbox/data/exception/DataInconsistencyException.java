package org.foo.sandbox.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DataInconsistencyException extends RuntimeException {

    private static final long serialVersionUID = -7085048022788021164L;

    public DataInconsistencyException() {
        super();
    }

    public DataInconsistencyException(String message) {
        super(message);
    }

    public DataInconsistencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
