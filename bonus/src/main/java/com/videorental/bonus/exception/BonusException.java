package com.videorental.bonus.exception;

import com.videorental.common.exception.BaseException;
import com.videorental.common.exception.ErrorCode;

/**
 * @author oleciwoj
 */
public class BonusException extends BaseException {
    public BonusException(String message) {
        super(ErrorCode.BONUS, message);
    }
}
