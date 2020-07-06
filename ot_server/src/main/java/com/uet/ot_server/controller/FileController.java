package com.uet.ot_server.controller;

import com.google.gson.Gson;
import com.uet.ot_server.model.OTFile;
import com.uet.ot_server.service.OTFileService;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping(value = "/files")
public class FileController {
    @Autowired
    private OTFileService otFileService;

//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
//        try {
//            OTFile otFile = fileService.storeFile(file);
//            String downloadPath = "/api/files/" + otFile.get_id();
//            return ResponseEntity.status(HttpStatus.OK).body(new ChannelSetting(otFile.get_id(), downloadPath));
//        } catch (BusinessServiceException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<OTFile> createNewFile(@RequestParam String diagramName) {
        try {
            Element element = new Element("uml");
            element.setAttribute("name", diagramName);

            Document document = new Document(element);

            XMLOutputter xmlOutputter = new XMLOutputter();
            xmlOutputter.setFormat(Format.getPrettyFormat());
            StringWriter writer = new StringWriter();
            xmlOutputter.output(document, writer);
            String result = writer.toString();
            OTFile otFile = otFileService.create(diagramName, result);
            return ResponseEntity.status(HttpStatus.OK).body(otFile);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getFiles() {
        Gson gson = new Gson();
        StringBuilder json = new StringBuilder();
        for (OTFile otFile : otFileService.getAll()) {
            json.append(gson.toJson(otFile)).append("//");
        }
        return ResponseEntity.status(HttpStatus.OK).body(json.toString());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<String> getDiagram(@PathVariable String id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(otFileService.findById(id).getContent());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
