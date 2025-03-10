package com.vt.spring_file_upload.controller.frontend;

import com.vt.spring_file_upload.constant.RestURIConstant;
import com.vt.spring_file_upload.infrastructure.model.body.BaseBodyResponse;
import com.vt.spring_file_upload.service.StorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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
                                    schema = @Schema(implementation = BaseBodyResponse.class),
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadFile(@RequestPart MultipartFile file) {
        storageService.upload(file);
        return ResponseEntity.ok("Upload file successfully!");
    }

    @Operation(
            summary = "Upload files",
            description = "Allows users to upload files to the server.",
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
                                    schema = @Schema(implementation = BaseBodyResponse.class),
                                    mediaType = "application/json"
                            )
                    )
            }
    )
    @PostMapping(value = "/batch-upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> batchUploadFile(@RequestPart List<MultipartFile> files) {
        storageService.batchUpload(files);
        return ResponseEntity.ok("Upload batch successfully!");
    }

    @GetMapping("/load/{filename}")
    public void loadFIle(@PathVariable String filename, HttpServletResponse response) {
        this.storageService.loadFile(filename, response);
        ResponseEntity.ok("success!");
    }
}
