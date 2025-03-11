package com.vt.spring_file_upload.service;

import com.vt.spring_file_upload.model.entity.FileEntity;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileEntity upload(MultipartFile file);

    List<FileEntity> batchUpload(List<MultipartFile> files);

    Page<FileEntity> findAll();
}
