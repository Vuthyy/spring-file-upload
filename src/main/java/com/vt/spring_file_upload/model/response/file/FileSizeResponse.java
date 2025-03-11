package com.vt.spring_file_upload.model.response.file;

import lombok.Data;

@Data
public class FileSizeResponse {

    private Long originalValue;
    private Double formatValue;
    private String formatType;
    private String normalized;
}
