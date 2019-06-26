package com.videorental.customer.domain;

import com.videorental.common.exception.BaseException;
import com.videorental.common.exception.ErrorCode;

/**
 * @author oleciwoj
 */
public class CustomerException extends BaseException {
    public CustomerException(ErrorCode code, String message) {
        super(code, message);
    }

    public CustomerException(ErrorCode code, int httpStatusCode) {
        super(code, httpStatusCode);
    }

    public CustomerException(ErrorCode code, int httpStatusCode, String message) {
        super(code, httpStatusCode, message);
    }

    public CustomerException(ErrorCode code, int httpStatusCode, String message, Throwable cause) {
        super(code, httpStatusCode, message, cause);
    }
}
