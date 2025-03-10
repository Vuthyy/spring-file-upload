package com.vt.spring_file_upload.service.impl;

import com.vt.spring_file_upload.exception.BadRequestException;
import com.vt.spring_file_upload.exception.InternalServerErrorException;
import com.vt.spring_file_upload.exception.NotFoundException;
import com.vt.spring_file_upload.service.StorageService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private static final String FILE_PATH = System.getProperty("user.dir") + "/files";

    @Override
    public void upload(MultipartFile file) {
        if (file.getSize() <= 0) throw new BadRequestException("No file provided!");

        String filename = UUID.randomUUID().toString();
        String sourceFilename = !Objects.requireNonNull(file.getOriginalFilename()).isEmpty() || !Objects
                .requireNonNull(file.getOriginalFilename()).isBlank() ? file.getOriginalFilename() : file.getName();
        String extension = sourceFilename.contains(".") ? sourceFilename.substring(sourceFilename.lastIndexOf(".")) : "";

        Path pth = !FILE_PATH.isBlank() || !FILE_PATH.isEmpty() ? Paths.get(FILE_PATH) : Paths.get("./");

        try {
            if (Files.notExists(pth))
                Files.createDirectories(pth);

            file.transferTo(pth.resolve(filename + extension));
        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @Override
    public void batchUpload(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            throw new BadRequestException("No files provided for batch upload!");
        }
        for (MultipartFile file : files) {
            upload(file);
        }
    }

    @Override
    public void loadFile(String filename, HttpServletResponse response) {
        try {
            Path p = Paths.get(FILE_PATH).resolve(filename);
            Resource resource = new UrlResource(p.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new NotFoundException("File not found!");
            }

            response.setHeader(HttpHeaders.CONTENT_TYPE, Files.probeContentType(p));

            FileCopyUtils.copy(resource.getInputStream(), response.getOutputStream());

        } catch (Exception ex) {
            if (ex instanceof NotFoundException) throw new NotFoundException(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
}
