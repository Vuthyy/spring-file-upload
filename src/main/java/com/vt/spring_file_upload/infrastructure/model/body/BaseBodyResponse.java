package com.vt.spring_file_upload.infrastructure.model.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Data
public class BaseBodyResponse implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean success;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BodyResponse body;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private StatusResponse status;

    public static ResponseEntity<Object> failed(String message, HttpStatusCode httpStatus) {
        BaseBodyResponse data = new BaseBodyResponse();
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode((short) httpStatus.value());
        statusResponse.setMessage(message);

        data.setSuccess(false);
        data.setStatus(statusResponse);

        return ResponseEntity.status(httpStatus).body(data);
    }
}
