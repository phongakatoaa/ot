package com.uet.ot_server.service;

import com.uet.ot_server.database.OTFileRepository;
import com.uet.ot_server.model.OTFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OTFileService {
    @Autowired
    private OTFileRepository repository;

    public OTFile findById(String id) {
        return repository.findBy_id(id);
    }

    public OTFile findByName(String name) {
        return repository.findByDiagramName(name);
    }

    public OTFile create(String name, String content) {
        OTFile otFile = new OTFile();
        otFile.setDiagramName(name);
        otFile.setContent(content);
        otFile.setTimestamp(LocalDateTime.now().toString());
        repository.save(otFile);
        return otFile;
    }

    public List<OTFile> getAll() {
        return repository.findAll();
    }
}
