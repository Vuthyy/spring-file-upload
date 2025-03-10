package com.vt.spring_file_upload.infrastructure.model.body;

import lombok.Data;

@Data
public class BodyResponse {
    private Object data;
    private PageResponse page;
}
