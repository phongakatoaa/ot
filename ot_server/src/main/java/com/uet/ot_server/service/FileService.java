package com.uet.ot_server.service;

import com.uet.ot_server.model.OTFile;
import com.uet.ot_server.service.exceptions.BusinessServiceException;
import com.uet.ot_server.service.exceptions.FileStorageException;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public interface FileService {
    Path createPath(String dir);

    boolean editFileName(String saveLocation, String oldFileName, String newFileName);

    boolean deleteFile(String filePath) throws IOException;

    OTFile storeFile(MultipartFile file) throws FileStorageException, BusinessServiceException;

    Resource loadFileAsResource(String fileName);

    OTFile getById(String id);

    List<OTFile> getAllFiles();
}
