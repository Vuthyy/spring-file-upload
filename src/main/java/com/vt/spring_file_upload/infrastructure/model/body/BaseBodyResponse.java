package com.vt.spring_file_upload.infrastructure.model.body;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vt.spring_file_upload.infrastructure.model.response.BaseResponse;
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

    public static ResponseEntity<BaseBodyResponse> success(Object data, String message) {
        short successCode = 200;
        BaseBodyResponse baseBodyResponse = new BaseBodyResponse();
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode(successCode);
        statusResponse.setMessage(message);

        BodyResponse bodyResponse = new BodyResponse();
        if (data instanceof BaseResponse) {
            bodyResponse.setData(data);
        }

        baseBodyResponse.setSuccess(true);
        baseBodyResponse.setStatus(statusResponse);
        baseBodyResponse.setBody(bodyResponse);

        return ResponseEntity.status(successCode).body(baseBodyResponse);
    }

    public static ResponseEntity<BaseBodyResponse> failed(String message, HttpStatusCode httpStatus) {
        BaseBodyResponse data = new BaseBodyResponse();
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode((short) httpStatus.value());
        statusResponse.setMessage(message);

        data.setSuccess(false);
        data.setStatus(statusResponse);

        return ResponseEntity.status(httpStatus).body(data);
    }

    public static ResponseEntity<Object> internalFailed(String message, HttpStatusCode httpStatus) {
        BaseBodyResponse data = new BaseBodyResponse();
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setCode((short) httpStatus.value());
        statusResponse.setMessage(message);

        data.setSuccess(false);
        data.setStatus(statusResponse);

        return ResponseEntity.status(httpStatus).body(data);
    }
}
