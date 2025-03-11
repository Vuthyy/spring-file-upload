package com.vt.spring_file_upload.model.response.file;

import com.vt.spring_file_upload.infrastructure.model.response.BaseResponse;
import lombok.Data;

import java.util.Date;

@Data
public class FileResponse implements BaseResponse {

    private Long id;
    private Date createdAt;
    private String name;
    private String OriginalName;
    private String url;
    private FileSizeResponse size;
    private String type;
}
