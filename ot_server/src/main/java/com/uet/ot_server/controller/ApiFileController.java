package com.uet.ot_server.controller;

import com.uet.ot_server.model.OTFile;
import com.uet.ot_server.model.ResponseMessage;
import com.uet.ot_server.service.FileService;
import com.uet.ot_server.service.exceptions.BusinessServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/files")
public class ApiFileController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadRepoFile(@RequestParam("file") MultipartFile file) {
        try {
            OTFile otFile = fileService.storeFile(file);
            String downloadPath = "/api/files/" + otFile.getName();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(otFile.get_id(), downloadPath));
        } catch (BusinessServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(value = "/{filename}", method = RequestMethod.GET)
    private ResponseEntity<Resource> getResourceResponseEntity(HttpServletRequest request, @PathVariable String filename) {
        Resource resource = fileService.loadFileAsResource(filename);
        String contentType = null;
        String fileName = Objects.requireNonNull(resource.getFilename());
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Could not determine file type!");
        }
        if (contentType == null) contentType = "application/octet-stream";
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }
}
