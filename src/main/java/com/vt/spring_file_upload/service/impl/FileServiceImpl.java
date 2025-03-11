package com.vt.spring_file_upload.service.impl;

import com.vt.spring_file_upload.exception.InternalServerErrorException;
import com.vt.spring_file_upload.model.entity.FileEntity;
import com.vt.spring_file_upload.repository.FileRepository;
import com.vt.spring_file_upload.service.FileService;
import com.vt.spring_file_upload.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    private final StorageService storageService;

    @Override
    public FileEntity upload(MultipartFile file) {
        String fileName = this.storageService.upload(file);

        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(fileName);
        fileEntity.setOriginalName(file.getOriginalFilename());

        fileEntity.setSize(file.getSize());

        fileEntity.setType(file.getContentType());

        String fileUrl = "http://localhost:8080/file/load/" + fileName;
        fileEntity.setUrl(fileUrl);

        try {
            return this.fileRepository.save(fileEntity);

        } catch (Exception ex) {
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @Override
    public List<FileEntity> batchUpload(List<MultipartFile> files) {
        List<FileEntity> fileEntities = new ArrayList<>();

        for (MultipartFile file : files) {
            fileEntities.add(this.upload(file));
        }
        return fileEntities;
    }

    @Override
    public Page<FileEntity> findAll() {
        return this.fileRepository.findAll(PageRequest.of(1, 10));
    }
}
