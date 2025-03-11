package com.vt.spring_file_upload.infrastructure.model.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class BodyResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private PageResponse page;
}
