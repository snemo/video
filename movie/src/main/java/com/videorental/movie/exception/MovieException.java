package com.videorental.movie.exception;

import com.videorental.common.exception.BaseException;
import com.videorental.common.exception.ErrorCode;

/**
 * @author oleciwoj
 */
public class MovieException extends BaseException {
    public MovieException(String message) {
        super(ErrorCode.MOVIE, message);
    }
}
