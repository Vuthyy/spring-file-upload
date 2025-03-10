package com.vt.spring_file_upload.infrastructure.model.body;

import lombok.Data;

@Data
public class PageResponse {

    private Long totalPage;
    private Long page;
    private Long totalCount;
    private Long pageSize;
}
