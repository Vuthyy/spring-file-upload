package com.vt.spring_file_upload.exception;

import com.vt.spring_file_upload.infrastructure.exception.BaseException;

public class NotFoundException extends BaseException {
    public NotFoundException(String message) {
        super(message);
    }
}
