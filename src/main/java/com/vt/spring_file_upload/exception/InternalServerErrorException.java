package com.vt.spring_file_upload.exception;

import com.vt.spring_file_upload.infrastructure.exception.BaseException;

public class InternalServerErrorException extends BaseException {
    public InternalServerErrorException(String message) {
        super(message);
    }
}
