package com.vt.spring_file_upload.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {

    void upload(MultipartFile file);

    void batchUpload(List<MultipartFile> files);

    void loadFile(String filename, HttpServletResponse response);
}
