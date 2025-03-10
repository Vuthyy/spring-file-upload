package com.vt.spring_file_upload.exception;

import com.vt.spring_file_upload.infrastructure.exception.BaseException;

public class ConflictException extends BaseException {
    public ConflictException(String message) {
        super(message);
    }
}
