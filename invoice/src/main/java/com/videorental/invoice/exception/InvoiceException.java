package com.videorental.invoice.exception;

import com.videorental.common.exception.BaseException;
import com.videorental.common.exception.ErrorCode;

/**
 * @author oleciwoj
 */
public class InvoiceException extends BaseException {
    public InvoiceException(String message) {
        super(ErrorCode.INVOICE, message);
    }
}
