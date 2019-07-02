package com.videorental.rental.exception;

import com.videorental.common.exception.BaseException;
import com.videorental.common.exception.ErrorCode;

/**
 * @author oleciwoj
 */
public class RentalException extends BaseException {
    public RentalException(String message) {
        super(ErrorCode.RENTAL, message);
    }
}
