package com.videorental.common.exception;

/**
 * @author oleciwoj
 */
public abstract class BaseException extends RuntimeException {

    private final ErrorCode code;
    private final int httpStatusCode;

    public BaseException(ErrorCode code, String message) {
        super(message);
        this.code = code;
        this.httpStatusCode = 500;
    }

    public BaseException(ErrorCode code, int httpStatusCode) {
        super();
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }

    public BaseException(ErrorCode code, int httpStatusCode, String message) {
        super(message);
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }

    public BaseException(ErrorCode code, int httpStatusCode, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }

    public ErrorCode code() {
        return this.code;
    }

    public int httpStatusCode() {
        return this.httpStatusCode;
    }
}
