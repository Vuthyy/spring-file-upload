package com.vt.spring_file_upload.mapper;

import com.vt.spring_file_upload.model.entity.FileEntity;
import com.vt.spring_file_upload.model.response.file.FileResponse;
import com.vt.spring_file_upload.model.response.file.FileSizeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(target = "size", source = "size", qualifiedByName = "mapFileSize")
    List<FileResponse> to(List<FileEntity> entities);

    @Mapping(target = "size", source = "size", qualifiedByName = "mapFileSize")
    FileResponse to(FileEntity entity);

    @Named("mapFileSize")
    default FileSizeResponse mapFileSize(Long size) {
        if (size == null) {
            return new FileSizeResponse(); // Return an empty object instead of null
        }

        FileSizeResponse fileSizeResponse = new FileSizeResponse();
        fileSizeResponse.setOriginalValue(size);

        // Convert size to readable format
        if (size >= 1_073_741_824) { // GB
            fileSizeResponse.setFormatValue(size / 1_073_741_824.0);
            fileSizeResponse.setFormatType("GB");
        } else if (size >= 1_048_576) { // MB
            fileSizeResponse.setFormatValue(size / 1_048_576.0);
            fileSizeResponse.setFormatType("MB");
        } else if (size >= 1024) { // KB
            fileSizeResponse.setFormatValue(size / 1024.0);
            fileSizeResponse.setFormatType("KB");
        } else { // Bytes
            fileSizeResponse.setFormatValue(size.doubleValue());
            fileSizeResponse.setFormatType("Bytes");
        }

        // Create a normalized representation
        fileSizeResponse.setNormalized(String.format("%.2f %s", fileSizeResponse.getFormatValue(), fileSizeResponse.getFormatType()));

        return fileSizeResponse;
    }

}
