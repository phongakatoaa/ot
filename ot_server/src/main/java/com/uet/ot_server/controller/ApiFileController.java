package com.uet.ot_server.controller;

import com.uet.ot_server.model.OTFile;
import com.uet.ot_server.model.ChannelSetting;
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
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/files")
public class ApiFileController {
    @Autowired
    private FileService fileService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            OTFile otFile = fileService.storeFile(file);
            String downloadPath = "/api/files/" + otFile.get_id();
            return ResponseEntity.status(HttpStatus.OK).body(new ChannelSetting(otFile.get_id(), downloadPath));
        } catch (BusinessServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OTFile>> getFiles() {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.getAllFiles());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private ResponseEntity<Resource> getResourceResponseEntity(HttpServletRequest request, @PathVariable String id) {
        OTFile otFile = fileService.getById(id);
        Resource resource = fileService.loadFileAsResource(otFile.getFileName());
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
                .header("fileName", fileName)
                .body(resource);
    }
}
