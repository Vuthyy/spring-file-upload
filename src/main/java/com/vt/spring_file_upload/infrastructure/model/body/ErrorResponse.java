package com.vt.spring_file_upload.infrastructure.model.body;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private Boolean success;
    private StatusResponse status;
}
