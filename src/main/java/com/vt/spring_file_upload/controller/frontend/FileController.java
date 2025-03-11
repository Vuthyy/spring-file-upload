package com.vt.spring_file_upload.controller.frontend;

import com.vt.spring_file_upload.constant.RestURIConstant;
import com.vt.spring_file_upload.infrastructure.model.body.BaseBodyResponse;
import com.vt.spring_file_upload.infrastructure.model.body.ErrorResponse;
import com.vt.spring_file_upload.mapper.FileMapper;
import com.vt.spring_file_upload.model.entity.FileEntity;
import com.vt.spring_file_upload.model.response.file.FileResponse;
import com.vt.spring_file_upload.service.FileService;
import com.vt.spring_file_upload.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = RestURIConstant.FILE)
@Tag(name = "[API]-Upload Files", description = "Controller for managing file uploads")
public class FileController {

    private final StorageService storageService;
    private final FileService fileService;
    private final FileMapper mapper;

    @Operation(
            summary = "Upload a file",
            description = "Allows users to upload a file to the server.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    schema = @Schema(implementation = BaseBodyResponse.class),
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400-500",
                            description = "Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseBodyResponse> uploadFile(@RequestPart MultipartFile file) {
        FileEntity entity = this.fileService.upload(file);
        FileResponse response = mapper.to(entity);
        return BaseBodyResponse.success(response, "Upload Successfully!");
    }

    @Operation(
            summary = "Upload files",
            description = "Allows users to upload files to the server.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = FileResponse.class)
                                    ),
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400-500",
                            description = "Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @PostMapping(value = "/batch-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<BaseBodyResponse> batchUploadFile(@RequestPart List<MultipartFile> files) {
        List<FileEntity> data = this.fileService.batchUpload(files);
        List<FileResponse> response = mapper.to(data);
        return BaseBodyResponse.success(response, "Upload Successfully!");
    }

    @Operation(
            summary = "Get all files",
            description = "Allows users to get all files in the server.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    schema = @Schema(implementation = FileResponse.class),
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400-500",
                            description = "Error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @GetMapping("/all")
    public ResponseEntity<BaseBodyResponse> getAll() {
        Page<FileEntity> data = this.fileService.findAll();

        return BaseBodyResponse.success(data, "Find all successfully!");
    }

    @GetMapping("/load/{filename}")
    public void loadFIle(@PathVariable String filename, HttpServletResponse response) {
        this.storageService.loadFile(filename, response);
        ResponseEntity.ok("success!");
    }
}
